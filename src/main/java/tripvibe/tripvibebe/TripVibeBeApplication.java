package tripvibe.tripvibebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tripvibe.tripvibebe.domain.Member;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TripVibeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripVibeBeApplication.class, args);
	}

}
