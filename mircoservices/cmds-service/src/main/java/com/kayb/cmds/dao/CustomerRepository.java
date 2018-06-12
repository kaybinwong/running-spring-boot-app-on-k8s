package com.kayb.cmds.dao;

import com.kayb.cmds.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     *  根据手机查找客户信息
     */
    Customer findByMobile(String mobile);
}
