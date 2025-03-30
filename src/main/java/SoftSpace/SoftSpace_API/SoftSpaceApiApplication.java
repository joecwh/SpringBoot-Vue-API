package SoftSpace.SoftSpace_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SoftSpaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftSpaceApiApplication.class, args);
	}

}
