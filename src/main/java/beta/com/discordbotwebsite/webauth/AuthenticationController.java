package beta.com.discordbotwebsite.webauth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("authentication", new AuthenticationRequest());
        return "auth/login";
    }

}