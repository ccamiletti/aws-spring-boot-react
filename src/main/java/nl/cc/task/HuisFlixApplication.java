package nl.cc.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HuisFlixApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuisFlixApplication.class, args);
	}

}
