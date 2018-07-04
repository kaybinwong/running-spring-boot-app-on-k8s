package com.kayb.cmds.controller;

import com.kayb.cmds.domain.Customer;
import com.kayb.cmds.domain.Properties;
import com.kayb.cmds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/1.0.0/customers")
    public Customer add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @GetMapping("/1.0.0/customers/{mobile}")
    public Customer custInfo(@PathVariable("mobile") String mobile) {
        return customerService.findCust(mobile);
    }

    @GetMapping("/1.0.0/properties/{custId}")
    public List<Properties> properties(@PathVariable("custId") Long custId) {
        return customerService.findProperties(custId);
    }

}
