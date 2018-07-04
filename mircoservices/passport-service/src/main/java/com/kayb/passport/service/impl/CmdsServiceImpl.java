package com.kayb.passport.service.impl;

import com.kayb.passport.dto.Customer;
import com.kayb.passport.service.CmdsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkaibin
 * @date 2018-06-11
 **/
@Slf4j
@Service("ribbon")
public class CmdsServiceImpl implements CmdsService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getByMobileByRibbonFallback")
    public Customer getByMobile(String mobile) {
        // test only
        if (System.currentTimeMillis() % 4 == 0) {
            try {
                log.error("wait 10s");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                ;
            }
        }
        return restTemplate.getForObject("http://cmds-service/1.0.0/cust/getCustInfo?mobile=" + mobile, Customer.class);
    }

    @Override
    public Customer add(Customer customer) {
        return restTemplate.postForEntity("http://cmds-service/1.0.0/customers/", customer, Customer.class).getBody();
    }

    public Customer getByMobileByRibbonFallback(String mobile) {
        log.error("getByMobileByRibbonFallback {}", mobile);
        return null;
    }
}
