package com.kayb.passport.controller;

import com.kayb.passport.dto.Customer;
import com.kayb.passport.service.CmdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/1.0.0/getUserByRibbon")
    public Customer ribbonService(String mobile){
        return ribbonService.getByMobile(mobile);
    }

    @GetMapping("/1.0.0/getUserByFeign")
    public Customer feignService(String mobile){
        return feignService.getByMobile(mobile);
    }
}
