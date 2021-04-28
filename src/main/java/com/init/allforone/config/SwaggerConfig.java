package com.init.allforone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //indicamos que esto es un archivo de config
@EnableSwagger2 //habilitamos swagger
public class SwaggerConfig {	
	
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2) 
						.select()//mediante select inicializamos un builder
						.apis(RequestHandlerSelectors.basePackage("com.init.allforone.rest"))
						.paths(PathSelectors.any()) //que url vamos a documentar
						.build(); //autoconstruye el objeto por nosotros
						
						
	}
	
}
