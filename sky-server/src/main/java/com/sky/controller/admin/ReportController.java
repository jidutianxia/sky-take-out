package com.sky.controller.admin;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.entity.User;
import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.DishVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计相关接口")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String TURNOVERKEY = "TURNOVERREPORT";
    public static final String USERKEY = "USERREPORT";

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计接口")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("营业额数据统计,参数为:{},{}", begin, end);

        String key = TURNOVERKEY + "begin" + begin.toString() + "end" + end.toString();
        log.info("营业额数据统计redis，key:{}", key);

        TurnoverReportVO turnoverReportVO = (TurnoverReportVO) redisTemplate.opsForValue().get(key);
        if (turnoverReportVO != null && turnoverReportVO.getTurnoverList() != null && turnoverReportVO.getDateList() != null) {
            return Result.success(turnoverReportVO);
        }
        turnoverReportVO = reportService.getTurnoverStatistics(begin, end);
        redisTemplate.opsForValue().set(key, turnoverReportVO, 18, TimeUnit.HOURS);
        return Result.success(turnoverReportVO);
    }

    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("用户数据统计,参数为:{},{}", begin, end);

        String key = USERKEY + "begin" + begin.toString() + "end" + end.toString();
        log.info("用户数据统计redis，key:{}", key);

        UserReportVO userReportVO = (UserReportVO) redisTemplate.opsForValue().get(key);
        if (userReportVO != null && userReportVO.getTotalUserList() != null
                && userReportVO.getNewUserList() != null
                && userReportVO.getDateList() != null) {
            return Result.success(userReportVO);
        }
        userReportVO = reportService.getUserStatistics(begin, end);
        redisTemplate.opsForValue().set(key, userReportVO, 18, TimeUnit.HOURS);
        return Result.success(userReportVO);
    }
}
