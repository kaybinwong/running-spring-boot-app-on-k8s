package com.kayb.cmds.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
@Data
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false)
    private String name;
    private int age;
    private String idCard = "";

    @Transient
    private List<Properties> properties = new ArrayList<>();
}
