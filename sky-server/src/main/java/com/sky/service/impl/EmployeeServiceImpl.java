package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //  后期需要进行md5加密，然后再进行比对
//        if (!password.equals(employee.getPassword())) {
//            //密码错误
//            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
//        }

        // 进行BCrypt加密密码
        if (!BCrypt.checkpw(password, employee.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
//        else {
//            System.out.println("密码正确" +"输入为"+ password);
//            System.out.println("密码正确" + employee.getPassword());
//        }


        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        //设置账号的状态，默认正常状态1表示正常 0表示锁定
        employee.setStatus(StatusConstant.ENABLE);

        // 哈希密码
        String originalPassword = "123456";
        String hashedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        //设置密码
        employee.setPassword(hashedPassword);

        //设置当前记录的创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置当前记录创建人id和修改人id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        BaseContext.removeCurrentId();

        employeeMapper.insert(employee);
    }

    @Override
    public Employee byIdSelect(String id) {
        return employeeMapper.byIdSelect(id);
    }

    @Override
    public PageResult pageSelect(EmployeePageQueryDTO employeePageQueryDTO) {
        int page = employeePageQueryDTO.getPage();
        int pageSize = employeePageQueryDTO.getPageSize();

        PageHelper.startPage(page, pageSize);
        Page<Employee> pageEmp = employeeMapper.pageSelect(employeePageQueryDTO);
        long total = pageEmp.getTotal();
        List<Employee> records = pageEmp.getResult();

        return new PageResult(total, records);
    }
}
