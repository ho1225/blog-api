package com.schh.blogapi.service.impl;

import com.schh.blogapi.entity.Comment;
import com.schh.blogapi.entity.Post;
import com.schh.blogapi.exception.BlogAPIException;
import com.schh.blogapi.exception.ResourceNotFoundException;
import com.schh.blogapi.payload.CommentDto;
import com.schh.blogapi.repository.CommentRepository;
import com.schh.blogapi.repository.PostRepository;
import com.schh.blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        // convert list of comment entities to list of comment dto
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Comment comment = ifCommentMatchPost(postId, commentId);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Comment comment = ifCommentMatchPost(postId, commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment save = commentRepository.save(comment);
        return mapToDto(save);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        ifCommentMatchPost(postId, commentId);
        commentRepository.deleteById(commentId);
    }

    private Comment ifCommentMatchPost(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the post");
        }
        return comment;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }



}
