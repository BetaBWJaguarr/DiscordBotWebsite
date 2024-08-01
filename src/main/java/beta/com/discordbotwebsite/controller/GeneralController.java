package beta.com.discordbotwebsite.controller;

import beta.com.discordbotwebsite.controller.config.BaseController;
import beta.com.discordbotwebsite.dcmodbot.discord.DiscordManager;
import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.model.GuildDTO;
import beta.com.discordbotwebsite.repos.UserRepository;
import jakarta.servlet.http.HttpSession;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class GeneralController extends BaseController {

    @Autowired
    DiscordManager discordManager;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String showMainPage(Model model) {
        return "general/main";
    }

    @GetMapping("/servers/manage-modules")
    public String showManageModules(Model model) {
        return "general/servers/managemodules/managemodules";
    }

    @GetMapping("/servers")
    public String showServers(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String discordId = user.getDiscordid();
                List<Guild> guilds = discordManager.getGuildsWhereUserIsAdmin(discordId);

                List<GuildDTO> guildDTOs = guilds.stream()
                        .map(guild -> {
                            String iconUrl = guild.getIconUrl();
                            if (iconUrl != null) {
                                iconUrl = "https://cdn.discordapp.com/icons/" + guild.getId() + "/" + iconUrl + ".png";
                            } else {
                                iconUrl = "/images/default-server.jpg";
                            }
                            return new GuildDTO(guild.getId(), guild.getName(), iconUrl);
                        })
                        .collect(Collectors.toList());

                guildDTOs.forEach(guildDTO -> System.out.println("GuildDTO: " + guildDTO.getName() + " | Icon URL: " + guildDTO.getIconUrl()));
                model.addAttribute("servers", guildDTOs);
            } else {
                model.addAttribute("servers", List.of());
            }
        } else {
            model.addAttribute("servers", List.of());
        }
        return "general/servers/servers";
    }


    @GetMapping("/commands")
    public String showCommands(Model model) {
        return "general/commands/commands";
    }
}
