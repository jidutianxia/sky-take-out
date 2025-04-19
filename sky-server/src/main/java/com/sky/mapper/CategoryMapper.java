package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
    void addCategory(Category category);

    Page<Category> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void byIdDelete();

    void update(Category category);
}
