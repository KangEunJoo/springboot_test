package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController //이 클래스를 restful로 만듦
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*@GetMapping //서버에서 무언가 얻으려고 할 때
	public String hello() {
		return "Hello World!";
	}*/
	
}