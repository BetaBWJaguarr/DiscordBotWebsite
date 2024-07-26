package beta.com.discordbotwebsite.dcmodbot.discord;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class JDAService {
    private static final Logger logger = Logger.getLogger(JDAService.class.getName());

    @Value("${discord.token}")
    private String token;

    private JDA jda;

    public JDA getJda() {
        return jda;
    }

    @PostConstruct
    public void init() {
        this.jda = JDABuilder.createDefault(token).build();
        logger.info("JDA instance initialized successfully.");
    }

}
