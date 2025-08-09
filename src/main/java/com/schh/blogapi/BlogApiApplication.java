package com.schh.blogapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App REST APIs",
                description = "Spring Boot Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "schh",
                        email = "schh@gmail.com"
                )
        )
)
public class BlogApiApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

}
