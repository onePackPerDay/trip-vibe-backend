package tripvibe.tripvibebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import tripvibe.tripvibebe.domain.Member;

@SpringBootApplication
public class TripVibeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripVibeBeApplication.class, args);
	}

}
