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

    @GetMapping("/servers/manage-modules/antispam")
    public String showManageModulesAntiSpam(Model model) {
        return "general/servers";
    }

    @GetMapping("/servers/manage-modules/antivirus")
    public String showManageModulesAntiVirus(Model model) {
        return "general/servers";
    }

    @GetMapping("/servers/manage-modules/verifysystem")
    public String showManageModulesVerifySystem(Model model) {
        return "general/servers";
    }

    @GetMapping("/servers/manage-modules/voiceaction")
    public String showManageModulesVoiceAction(Model model) {
        return "general/servers";
    }

    @GetMapping("/servers")
    public String showServers(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            model.addAttribute("servers", List.of());
            return "general/servers/servers";
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            model.addAttribute("servers", List.of());
            return "general/servers/servers";
        }

        User user = optionalUser.get();
        String discordId = user.getDiscordid();
        List<Guild> guilds = discordManager.getGuildsWhereUserIsAdmin(discordId);

        List<GuildDTO> guildDTOs = guilds.stream()
                .map(guild -> {
                    String iconUrl = Optional.ofNullable(guild.getIconUrl())
                            .map(url -> "https://cdn.discordapp.com/icons/" + guild.getId() + "/" + url + ".png")
                            .orElse("/images/default-server.jpg");
                    return new GuildDTO(guild.getId(), guild.getName(), iconUrl);
                })
                .collect(Collectors.toList());

        model.addAttribute("servers", guildDTOs);
        return "general/servers/servers";
    }


    @GetMapping("/commands")
    public String showCommands(Model model) {
        return "general/commands/commands";
    }
}
