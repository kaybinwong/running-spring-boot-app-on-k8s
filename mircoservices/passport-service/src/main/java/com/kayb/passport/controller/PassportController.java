package com.kayb.passport.controller;

import com.kayb.passport.dto.Customer;
import com.kayb.passport.service.CmdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangkaibin
 * @date 2018-06-11
 **/
@RestController
public class PassportController {
    @Autowired
    private CmdsService feignService;

    @Qualifier("ribbon")
    @Autowired
    private CmdsService ribbonService;

    @PostMapping("/1.0.0/accounts")
    public Customer ribbonService(@RequestBody Customer customer){
        return feignService.add(customer);
    }

    @GetMapping("/1.0.0/accounts/{mobile}")
    public Customer feignService(@PathVariable("mobile") String mobile){
        return feignService.getByMobile(mobile);
    }
}
