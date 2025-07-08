package com.schh.blogapi.service;

import com.schh.blogapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
}
