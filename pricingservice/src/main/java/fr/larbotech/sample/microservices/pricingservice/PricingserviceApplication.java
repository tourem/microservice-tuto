package fr.larbotech.sample.microservices.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PricingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingserviceApplication.class, args);
	}
}
