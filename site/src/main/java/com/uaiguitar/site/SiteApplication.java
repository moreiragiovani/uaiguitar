package com.uaiguitar.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EntityScan("com/uaiguitar/site/entidades")
@EnableJpaRepositories(value = "com/uaiguitar/site/repository")
@SpringBootApplication
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
		System.out.println(" --->>> Senha: " + new BCryptPasswordEncoder().encode("123"));
	}

}
