package com.schh.blogapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


@Data
@Schema(
    description = "PostDto Model Information"
)
public class PostDto {
    private Long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;


    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty(message = "Content should not be null or empty")
    private String content;

    @Schema(
            description = "Comments of Blog Post"
    )
    private Set<CommentDto> comments;

    @Schema(
            description = "Category ID of Blog Post"
    )
    private Long categoryId;
}
