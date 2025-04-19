package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmpEditPasswordDTO implements Serializable {
    private Integer id;
    private String newPassword;
    private String oldPassword;

}
