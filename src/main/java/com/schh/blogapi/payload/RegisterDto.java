package com.schh.blogapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    public String name;
    public String username;
    public String email;
    public String password;
}
