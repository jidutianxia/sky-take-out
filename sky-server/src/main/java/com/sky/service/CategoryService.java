package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    PageResult pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void byIdDelete(String id);

    void updateCategory(CategoryDTO categoryDTO);

    void onOrOff(String status, String id);
}
