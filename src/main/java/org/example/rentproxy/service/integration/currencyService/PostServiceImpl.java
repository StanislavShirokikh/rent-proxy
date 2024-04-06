package org.example.rentproxy.service.integration.currencyService;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public PostDto save(PostDto postDto) {

        return null;
    }

    @Override
    public void deletePostById(long id) {

    }

    @Override
    public PostDto findPostById(long id) {
        return null;
    }

    @Override
    public PostDto updatePost(PostDto post) {
        return null;
    }

    @Override
    public List<PostDto> findPostByFilter(Filter filter) {
        return null;
    }
}
