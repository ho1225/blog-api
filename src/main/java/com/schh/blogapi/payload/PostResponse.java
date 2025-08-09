package com.schh.blogapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Post Response Model for Pagination and Sorting Information"
)
public class PostResponse {
    @Schema(
            description = "List of PostDto"
    )
    private List<PostDto> content;

    @Schema(
            description = "page number"
    )
    private int pageNo;

    @Schema(
            description = "page size"
    )
    private int pageSize;

    @Schema(
            description = "number of posts"
    )
    private long totalElements;

    @Schema(
            description = "number of pages"
    )
    private int totalPages;

    @Schema(
            description = "isLast"
    )
    private boolean last;
}
