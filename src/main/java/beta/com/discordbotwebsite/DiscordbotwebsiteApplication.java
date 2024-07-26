package beta.com.discordbotwebsite;

import beta.com.discordbotwebsite.dcmodbot.discord.JDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DiscordbotwebsiteApplication {

    private final JDAService jdaService;

    @Autowired
    public DiscordbotwebsiteApplication(JDAService jdaService) {
        this.jdaService = jdaService;
    }

    public static void main(final String[] args) {
        SpringApplication.run(DiscordbotwebsiteApplication.class, args);
    }

}
