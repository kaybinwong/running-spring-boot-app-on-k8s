package com.kayb.passport.service.impl;

import com.kayb.passport.dto.Customer;
import com.kayb.passport.service.CmdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkaibin
 * @date 2018-06-11
 **/
@Service("ribbon")
public class CmdsServiceImpl implements CmdsService {
    @Autowired
    private RestTemplate restTemplate;

    public Customer getByMobile(String mobile) {
        return restTemplate.getForObject("http://cmds-service/1.0.0/cust/getCustInfo?mobile=" + mobile, Customer.class);
    }
}
