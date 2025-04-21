package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(value = OperationType.INSERT)
    void addCategory(Category category);

    Page<Category> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void byIdDelete();

    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    List<Category> list(String type);
}
