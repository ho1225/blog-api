package com.schh.blogapi.service;

import com.schh.blogapi.payload.PostDto;
import com.schh.blogapi.payload.PostResponse;


public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

}
