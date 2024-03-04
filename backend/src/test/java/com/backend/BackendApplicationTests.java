package com.backend;

import com.backend.services.EmailServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
        System.out.println("Testing services..");
        EmailServiceTest est = new EmailServiceTest();
        est.testSendEmail();
	}

}
