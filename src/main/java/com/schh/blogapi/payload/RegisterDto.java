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
        description = "RegisterDto Model Information"
)
public class RegisterDto {
    public String name;
    public String username;
    public String email;
    public String password;
}
