package beta.com.discordbotwebsite.controller;

import beta.com.discordbotwebsite.controller.config.BaseController;
import beta.com.discordbotwebsite.dcmodbot.discord.DiscordManager;
import beta.com.discordbotwebsite.dcmodbot.serversettings.ServerSettingsService;
import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.model.GuildDTO;
import beta.com.discordbotwebsite.repos.UserRepository;
import jakarta.servlet.http.HttpSession;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class GeneralController extends BaseController {

    @Autowired
    private DiscordManager discordManager;

    @Autowired
    private ServerSettingsService serverSettingsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showMainPage(Model model) {
        return "general/main";
    }

    private boolean isUserAdminForServer(String userId, String serverId) {
        List<Guild> userGuilds = discordManager.getGuildsWhereUserIsAdmin(userId);
        return userGuilds.stream().anyMatch(guild -> guild.getId().equals(serverId));
    }


    @GetMapping("/servers/manage-modules/{serverId}")
    public String showManageModules(@PathVariable String serverId, Model model) {
        model.addAttribute("serverId", serverId);
        return "general/servers/managemodules/managemodules";
    }

    //AntiSpam Feature Controller
    @GetMapping("/servers/manage-modules/{serverId}/antispam")
    public String showManageModulesAntiSpam(@PathVariable String serverId, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }

        User user = optionalUser.get();
        String discordId = user.getDiscordid();

        if (!isUserAdminForServer(discordId, serverId)) {
            return "redirect:/access-denied";
        }

        boolean isAntiSpamEnabled = serverSettingsService.isAntiSpamEnabled(serverId);
        Integer messageLimit = serverSettingsService.getAntiSpamMessageLimit(serverId);
        Integer timeLimit = serverSettingsService.getAntiSpamTimeLimit(serverId);

        model.addAttribute("antispamEnabled", isAntiSpamEnabled);
        model.addAttribute("messageLimit", messageLimit != null ? messageLimit : 0);
        model.addAttribute("timeLimit", timeLimit != null ? timeLimit : 0);
        model.addAttribute("serverId", serverId);

        return "general/servers/managemodules/antispam/antispammodules";
    }


    @PostMapping("/servers/manage-modules/antispam/update")
    public String updateAntiSpamSettings(
            @RequestParam("serverId") String serverId,
            @RequestParam(name = "antispamEnabled", defaultValue = "false") boolean antispamEnabled,
            @RequestParam(name = "messageLimit", defaultValue = "0") int messageLimit,
            @RequestParam(name = "timeLimit", defaultValue = "0") int timeLimit,
            Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }

        User user = optionalUser.get();
        String discordId = user.getDiscordid();

        if (!isUserAdminForServer(discordId, serverId)) {
            return "redirect:/access-denied";
        }

        serverSettingsService.setAntiSpamEnabled(serverId, antispamEnabled);
        serverSettingsService.setAntiSpamMessageLimit(serverId, messageLimit);
        serverSettingsService.setAntiSpamTimeLimit(serverId, timeLimit);

        model.addAttribute("antispamEnabled", antispamEnabled);
        model.addAttribute("messageLimit", messageLimit);
        model.addAttribute("timeLimit", timeLimit);

        return "redirect:/servers/manage-modules/" + serverId + "/antispam";
    }
    //AntiSpam Feature Controller

    @GetMapping("/servers/manage-modules/{serverId}/antivirus")
    public String showManageModulesAntiVirus(@PathVariable String serverId, Model model) {
        model.addAttribute("serverId", serverId);
        return "general/servers/managemodules/antivirus";
    }

    @GetMapping("/servers/manage-modules/{serverId}/verifysystem")
    public String showManageModulesVerifySystem(@PathVariable String serverId, Model model) {
        model.addAttribute("serverId", serverId);
        return "general/servers/managemodules/verifysystem";
    }

    @GetMapping("/servers/manage-modules/{serverId}/voiceaction")
    public String showManageModulesVoiceAction(@PathVariable String serverId, Model model) {
        model.addAttribute("serverId", serverId);
        return "general/servers/managemodules/voiceaction";
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
