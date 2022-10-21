package com.truman.BackgroundSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.truman.BackgroundSystem.mapper")
@SpringBootApplication
@RestController
public class BackgroundSystemApplication {

	@GetMapping("/deploy")
	public String test(){
		return "Successfully deploy spring boot to azure :)"
	}

	public static void main(String[] args) {
		SpringApplication.run(BackgroundSystemApplication.class, args);
	}

}
