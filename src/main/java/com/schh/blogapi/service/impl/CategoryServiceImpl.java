package com.schh.blogapi.service.impl;

import com.schh.blogapi.entity.Category;
import com.schh.blogapi.exception.ResourceNotFoundException;
import com.schh.blogapi.payload.CategoryDto;
import com.schh.blogapi.repository.CategoryRepository;
import com.schh.blogapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);

        Category newCategory = categoryRepository.save(category);
        return mapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id.toString()));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> allCategories = categoryRepository.findAll();
        for (Category category : categoryRepository.findAll()) {
            categoryDtoList.add(mapper.map(category, CategoryDto.class));
        }
        return allCategories.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }


}
