package com.alura;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@OpenAPIDefinition(info = @Info(title = "API foro alura", version = "1.0", description = "Es el trabajo final del curso de Oracle One Education"))
@ComponentScan("com.alura")
@SpringBootApplication
public class ForoInicialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}


}
