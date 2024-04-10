package br.com.instrumental_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.instrumental_rental")
public class InstrumentalRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentalRentalApplication.class, args);
	}

}
