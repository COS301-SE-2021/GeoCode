package tech.geocodeapp.geocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.EntityListeners;

@SpringBootApplication
@EntityScan(basePackages = {"tech.geocodeapp.geocode", "io.swagger"})
public class GeocodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocodeApplication.class, args);
	}

}
