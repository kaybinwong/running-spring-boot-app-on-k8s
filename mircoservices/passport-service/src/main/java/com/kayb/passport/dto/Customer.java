package com.kayb.passport.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
@Data
public class Customer {

    private Long id;
    private String mobile;
    private String name;
    private int age;
}
