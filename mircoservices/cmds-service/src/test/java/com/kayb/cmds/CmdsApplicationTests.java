package com.kayb.cmds;

import com.kayb.cmds.domain.Customer;
import com.kayb.cmds.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Slf4j
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CmdsApplication.class)
public class CmdsApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void notExist() {
		log.info("test notExist");
		Customer customer = customerService.findCust("13800138000");
		assertNull(customer);
	}

	@Test
	public void exist() {
		log.info("test exist");
		Customer customer = customerService.findCust("13800138001");
		assertNotNull(customer);
	}

}
