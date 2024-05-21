package de.jkurzmann.BeerPong;

import de.jkurzmann.BeerPong.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeerPongApplication {

    private static final Logger log = LoggerFactory.getLogger(BeerPongApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeerPongApplication.class, args);
	}

    @Bean
    CommandLineRunner runner()
    {
        return args ->
        {
            User user = new User(1, "JJK", "business.kurzmann@gmail.com", "Jakob",
                    "Kurzmann", "");
            log.info("User: " +  user);
        };
    }


}
