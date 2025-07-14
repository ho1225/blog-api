package com.schh.blogapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;

    @NotEmpty(message = "Content should not be null or empty")
    private String content;
    private Set<CommentDto> comments;
}
