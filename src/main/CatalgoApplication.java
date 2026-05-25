package com.ecommerce.catalgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalgoApplication.class, args);
	}

	@Configuration
	public static class Myconfiguration{
		@Bean
		public WebMvcConfigurer corsConfigurer(){
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
				}
			};
		}
	}
}
