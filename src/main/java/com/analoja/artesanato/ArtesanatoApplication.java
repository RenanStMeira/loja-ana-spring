package com.analoja.artesanato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ArtesanatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtesanatoApplication.class, args);
	}

}
