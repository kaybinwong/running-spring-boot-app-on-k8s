package com.kayb.cmds.controller;

import com.kayb.cmds.domain.Customer;
import com.kayb.cmds.domain.Properties;
import com.kayb.cmds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
@RefreshScope
@RestController
public class CustomerController {

    @Value("${profile}")
    private String profile;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/refresh")
    public String refresh() {
        return profile;
    }

    @GetMapping("/1.0.0/cust/getCustInfo")
    public Customer custInfo(String mobile) {
        return customerService.findCust(mobile);
    }

    @GetMapping("/1.0.0/properties/getByCustId")
    public List<Properties> properties(Long custId) {
        return customerService.findProperties(custId);
    }

}
