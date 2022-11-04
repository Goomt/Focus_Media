package com.example.demo;

//import com.example.demo.entity.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class FocusMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusMediaApplication.class, args);
	}

}
