package beta.com.discordbotwebsite.dcmodbot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class DiscordManager {

    private static final Logger logger = Logger.getLogger(DiscordManager.class.getName());

    private final JDAService jdaService;

    @Autowired
    public DiscordManager(JDAService jdaService) {
        this.jdaService = jdaService;
    }

    public Optional<Guild> isUserAdminInGuild(String discordId, String guildId) {
        try {
            JDA jda = jdaService.getJda();
            Guild guild = jda.getGuildById(guildId);
            if (guild == null) {
                logger.warning("Guild not found with ID: " + guildId);
                return Optional.empty();
            }

            guild.loadMembers();

            Member member = guild.getMemberById(discordId);
            if (member == null) {
                logger.info("User not found in the guild with ID: " + discordId);
                return Optional.empty();
            }

            if (member.hasPermission(Permission.ADMINISTRATOR)) {
                logger.info("User is an administrator in guild: " + guild.getName());
                return Optional.of(guild);
            } else {
                logger.info("User is not an administrator in guild: " + guild.getName());
                return Optional.empty();
            }
        } catch (InsufficientPermissionException e) {
            logger.severe("Insufficient permissions to access guild or member details: " + e.getMessage());
            return Optional.empty();
        } catch (ErrorResponseException e) {
            logger.severe("Error response from Discord API: " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean isUserAdminInAnyGuild(String discordId) {
        JDA jda = jdaService.getJda();
        List<Guild> guilds = jda.getGuilds();

        for (Guild guild : guilds) {
            Optional<Guild> optionalGuild = isUserAdminInGuild(discordId, guild.getId());

            if (optionalGuild.isPresent()) {
                return true;
            }
        }

        return false;
    }

    public Optional<Member> findUserByDiscordId(String discordId) {
        JDA jda = jdaService.getJda();
        List<Guild> guilds = jda.getGuilds();

        for (Guild guild : guilds) {
            Member member = guild.getMemberById(discordId);
            if (member != null) {
                logger.info("User found in guild: " + guild.getName());
                return Optional.of(member);
            }
        }

        logger.info("User not found in any guild with Discord ID: " + discordId);
        return Optional.empty();
    }

    public boolean isUserAdmin(String discordId) {
        Optional<Member> user = findUserByDiscordId(discordId);
        if (user.isEmpty()) {
            logger.info("User not found in any guild with Discord ID: " + discordId);
            return false;
        }

        return isUserAdminInAnyGuild(discordId);
    }

    public List<Guild> getGuildsWhereUserIsAdmin(String discordId) {
        List<Guild> adminGuilds = new ArrayList<>();
        JDA jda = jdaService.getJda();
        List<Guild> guilds = jda.getGuilds();

        for (Guild guild : guilds) {
            Optional<Guild> optionalGuild = isUserAdminInGuild(discordId, guild.getId());

            if (optionalGuild.isPresent()) {
                adminGuilds.add(optionalGuild.get());
            }
        }

        return adminGuilds;
    }
}
