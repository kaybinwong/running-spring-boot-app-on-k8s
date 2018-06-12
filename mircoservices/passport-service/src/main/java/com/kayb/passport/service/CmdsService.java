package com.kayb.passport.service;

import com.kayb.passport.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huangkaibin
 * @date 2018-06-11
 **/
@FeignClient(name="cmds-service")
public interface CmdsService {

    @GetMapping(path = "/1.0.0/cust/getCustInfo")
    public Customer getByMobile(@RequestParam("mobile") String mobile);

}
