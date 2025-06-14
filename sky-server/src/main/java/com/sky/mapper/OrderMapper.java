package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);

    Page<Orders> getHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id=#{id}")
    Orders getById(String id);
    void update(Orders orders);

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);


//    @MapKey("order_date")
//    Map<String, String> getTurnoverList(@Param("dateList") List<LocalDate> dateList);


    Double sumByMap(Map map);
}
