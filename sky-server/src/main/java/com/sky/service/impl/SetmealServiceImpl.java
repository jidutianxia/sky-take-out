package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.AccountLockedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private DishMapper dishMapper;


    @Override
    public void newAdd(SetmealDTO setmealDTO) {
        setmealDTO.setStatus(StatusConstant.DISABLE);

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        String name = setmeal.getName();
        if (!setmealMapper.soleName(name)) {
            if (setmealDTO.getSetmealDishes() != null){
                setmealMapper.newAdd(setmeal);

                Long setmealId = setmeal.getId();

                List<SetmealDish> setmealDTOList = setmealDTO.getSetmealDishes();
                setmealDTOList.forEach(item ->{
                    item.setSetmealId(setmealId);
                });

                setmealDishMapper.insertDish(setmealDTOList);
            }else {
                log.info("当前套餐关联菜品为空");
            }
        }else {
            throw new AccountLockedException(MessageConstant.ALREADY_EXISTS);
        }
    }

    @Override
    public PageResult pageSelect(SetmealPageQueryDTO setmealPageQueryDTO) {
        int pageNum = setmealPageQueryDTO.getPage();
        int pageSize = setmealPageQueryDTO.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
