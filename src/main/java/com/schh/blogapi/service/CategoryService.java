package com.schh.blogapi.service;

import com.schh.blogapi.payload.CategoryDto;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);
}
