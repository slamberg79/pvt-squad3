package studentcapture.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import studentcapture.config.StudentCaptureApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StudentCaptureApplication.class)
@WebAppConfiguration
public class SpringLoginApplicationTests {

	@Test
	public void contextLoads() {
	}

}
