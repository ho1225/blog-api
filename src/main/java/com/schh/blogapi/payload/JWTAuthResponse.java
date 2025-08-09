package com.schh.blogapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "JWT Authentication Model Information"
)
public class JWTAuthResponse {
    @Schema(
            description = "JWT"
    )
    private String accessToken;
    private final String tokenType = "Bearer";
}
