package com.schh.blogapi.service;

import com.schh.blogapi.payload.LoginDto;
import com.schh.blogapi.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
