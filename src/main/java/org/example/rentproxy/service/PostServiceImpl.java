package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.PostDtoMapper;
import org.example.rentproxy.mapper.PostMapper;
import org.example.rentproxy.repository.jpa.PostRepository;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.service.integration.currencyService.apiLayer.ApiLayerService;
import org.example.rentproxy.service.user.UserParamName;
import org.example.rentproxy.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ApiLayerService apiLayerService;
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
    public PostDto findPostById(String username, long id) {
        Post post = postRepository.findPostById(id);
        PostDto postDto = postDtoMapper.convertToPostDto(post);
        changeCurrency(username, postDto);

        return postDto;
    }

    private void changeCurrency(String username, PostDto postDto) {
        long userId = userService.findUserByName(username).getId();
        String userCurrencyValue = userService.getUserParam(userId,
                UserParamName.DEFAULT_CURRENCY,
                String.class
        );

        if (postDto.getRentConditionInfoDto().getCurrency().equalsIgnoreCase(userCurrencyValue)) {
            return;
        }

        double price = apiLayerService.convertCurrency(
                postDto.getRentConditionInfoDto().getCurrency(),
                userCurrencyValue,
                String.valueOf(postDto.getRentConditionInfoDto().getPrice())
        ).getResult();

        postDto.getRentConditionInfoDto().setPrice(price);

        double deposit = apiLayerService.convertCurrency(
                postDto.getRentConditionInfoDto().getCurrency(),
                userCurrencyValue,
                String.valueOf(postDto.getRentConditionInfoDto().getDeposit())
        ).getResult();

        postDto.getRentConditionInfoDto().setDeposit(deposit);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Post post = postRepository.updatePost(postMapper.convertToPost(postDto));
        return postDtoMapper.convertToPostDto(post);
    }

    @Override
    public List<PostDto> findPostByFilter(Filter filter) {
        List<Post> posts = postRepository.findPostByFilter(filter);
        return postDtoMapper.convertToListPostDto(posts);
    }
}
