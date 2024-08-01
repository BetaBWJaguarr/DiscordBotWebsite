package beta.com.discordbotwebsite.dcmodbot.discord;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
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
        try {
            this.jda = JDABuilder.createDefault(token, EnumSet.of(
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_PRESENCES
            )).build();
            logger.info("JDA instance initialized successfully.");
        } catch (Exception e) {
            logger.severe("Failed to initialize JDA instance: " + e.getMessage());
        }
    }
}
