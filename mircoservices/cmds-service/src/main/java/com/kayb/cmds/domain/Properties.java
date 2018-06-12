package com.kayb.cmds.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
@Data
@Entity(name = "properties")
public class Properties {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "cust_id", nullable = false)
    private Long custId;
    @Column(name = "project", nullable = false)
    private String project;
    @Column(name = "building", nullable = false)
    private String building;
    @Column(name = "unit", nullable = false)
    private String unit;
}
