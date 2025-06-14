package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.OrderDetail;
import com.sky.vo.SalesTop10ReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDetailMapper {
    void inserBatch(List<OrderDetail> orderDetailList);

    List<OrderDetail> getByOrderId(Long orderId);

    List<GoodsSalesDTO> countByMap(Map dataMap);
}
