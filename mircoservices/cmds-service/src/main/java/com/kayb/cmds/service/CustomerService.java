package com.kayb.cmds.service;

import com.kayb.cmds.domain.Customer;
import com.kayb.cmds.domain.Properties;

import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
public interface CustomerService {
    Customer findCust(String mobile);

    List<Properties> findProperties(Long mobile);
}
