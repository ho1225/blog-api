package com.schh.blogapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Category Model Information"
)
public class CategoryDto {

    private Long id;
    @Schema(
            description = "Category name"
    )
    private String name;
    @Schema(
            description = "Category description"
    )
    private String description;
}
