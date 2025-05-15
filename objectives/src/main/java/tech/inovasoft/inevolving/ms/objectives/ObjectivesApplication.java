package tech.inovasoft.inevolving.ms.objectives;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ObjectivesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObjectivesApplication.class, args);
	}

}
