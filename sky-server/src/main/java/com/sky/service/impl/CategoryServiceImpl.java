package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();
        category.setStatus(StatusConstant.ENABLE);

        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.addCategory(category);
    }

    @Override
    public PageResult pageSelect(CategoryPageQueryDTO categoryPageQueryDTO) {
        int page = categoryPageQueryDTO.getPage();
        int pageSize = categoryPageQueryDTO.getPageSize();

        PageHelper.startPage(page, pageSize);
        Page<Category> pageCategory = categoryMapper.pageSelect(categoryPageQueryDTO);
        long total = pageCategory.getTotal();
        List<Category> records = pageCategory.getResult();

        return new PageResult(total, records);
    }

    @Override
    public void byIdDelete(String id) {
        categoryMapper.byIdDelete();
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.update(category);
    }

    @Override
    public void onOrOff(String status, String id) {
        Category category = Category.builder()
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .status(Integer.valueOf(status))
                .id(Long.valueOf(id))
                .build();

        categoryMapper.update(category);
    }
}
