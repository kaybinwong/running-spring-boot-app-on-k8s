package com.kayb.passport.service.impl;

import com.kayb.passport.dto.Customer;
import com.kayb.passport.service.CmdsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huangkaibin
 * @date 2018-06-13
 **/
@Slf4j
public class CmdsFallbackService implements CmdsService {
    @Override
    public Customer getByMobile(String mobile) {
        log.error("feign fallback, {}", mobile);
        return null;
    }
}
