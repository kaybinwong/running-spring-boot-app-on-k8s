package com.kayb.cmds.service.impl;

import com.kayb.cmds.dao.CustomerRepository;
import com.kayb.cmds.dao.PropertiesRepository;
import com.kayb.cmds.domain.Customer;
import com.kayb.cmds.domain.Properties;
import com.kayb.cmds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public Customer findCust(String mobile) {
        return customerRepository.findByMobile(mobile);
    }

    @Override
    public List<Properties> findProperties(Long custId) {
        return propertiesRepository.findByCustId(custId);
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }
}
