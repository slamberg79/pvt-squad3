package login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import login.SpringLoginApplication;
@SpringBootApplication
public class SpringLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringLoginApplication.class, args);
	}
}
