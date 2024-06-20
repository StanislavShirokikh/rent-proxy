package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.PostDtoMapper;
import org.example.rentproxy.mapper.PostMapper;
import org.example.rentproxy.repository.jpa.PostRepository;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.user.UserParamName;
import org.example.rentproxy.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CurrencyService currencyService;
    private final PostDtoMapper postDtoMapper;
    private final PostMapper postMapper;

    @Override
    public PostDto save(PostDto postDto) {
        Post post = postRepository.save(postMapper.convertToPost(postDto));
        return postDtoMapper.convertToPostDto(post);
    }

    @Override
    public void deletePostById(long id) {
        postRepository.deletePostById(id);
    }

    @Override
    public PostDto findPostById(String username, long id, String currencyValueFromSession) {
        Post post = postRepository.findPostById(id);
        PostDto postDto = postDtoMapper.convertToPostDto(post);
        changeCurrencyIfNeeded(username, postDto,  currencyValueFromSession);

        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Post post = postRepository.updatePost(postMapper.convertToPost(postDto));
        return postDtoMapper.convertToPostDto(post);
    }

    @Override
    public List<PostDto> findPostByFilter(Filter filter, String username, String currencyValueFromSession) {
        List<Post> posts = postRepository.findPostByFilter(filter);

        List<PostDto> postDtos = postDtoMapper.convertToListPostDto(posts);
        if (postDtos != null && !postDtos.isEmpty()) {
            postDtos.forEach(postDto -> changeCurrencyIfNeeded(username, postDto, currencyValueFromSession));
        }

        return postDtos;
    }

    private void changeCurrencyIfNeeded(String username, PostDto postDto, String currencyValue) {
        if (username != null) {
            changeCurrencyForAuthenticatedUser(username, postDto);
        } else {
            changeCurrencyForAnonymousUser(postDto, currencyValue);
        }
    }

    private void changeCurrencyForAnonymousUser(PostDto postDto, String currencyValueFromSession) {
        if (currencyValueFromSession != null) {
            changeCurrency(currencyValueFromSession, postDto);
        }
    }

    private void changeCurrencyForAuthenticatedUser(String username, PostDto postDto) {
        long userId = userService.findUserByName(username).getId();
        boolean isAutoConversionEnabled = userService.getUserParam(userId, UserParamName.AUTO_CONVERSION, Boolean.class);
        if (isAutoConversionEnabled) {
            String userCurrencyValue = userService.getUserParam(userId, UserParamName.DEFAULT_CURRENCY, String.class);
            changeCurrency(userCurrencyValue, postDto);
        }
    }

    private void changeCurrency(String currencyValue, PostDto postDto) {
        if (postDto.getRentConditionInfoDto().getCurrency().equalsIgnoreCase(currencyValue)) {
            return;
        }

        double price = currencyService.convertCurrency(
                postDto.getRentConditionInfoDto().getCurrency(),
                currencyValue,
                postDto.getRentConditionInfoDto().getPrice()
        ).getResult();

        postDto.getRentConditionInfoDto().setPrice(price);

        double deposit = currencyService.convertCurrency(
                postDto.getRentConditionInfoDto().getCurrency(),
                currencyValue,
                postDto.getRentConditionInfoDto().getDeposit()
        ).getResult();

        postDto.getRentConditionInfoDto().setDeposit(deposit);
        postDto.getRentConditionInfoDto().setCurrency(currencyValue);
    }
}
