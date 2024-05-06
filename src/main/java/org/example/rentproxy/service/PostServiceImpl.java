package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.PostDtoMapper;
import org.example.rentproxy.mapper.PostMapper;
import org.example.rentproxy.repository.PostRepository;
import org.example.rentproxy.repository.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
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
    public PostDto findPostById(long id) {
        Post post = postRepository.findPostById(id);
        return postDtoMapper.convertToPostDto(post);
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
