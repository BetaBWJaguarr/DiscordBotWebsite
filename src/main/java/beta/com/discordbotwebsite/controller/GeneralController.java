package beta.com.discordbotwebsite.controller;

import beta.com.discordbotwebsite.controller.config.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class GeneralController extends BaseController {

    @GetMapping("/")
    public String showMainPage(Model model) {
        return "general/main";
    }
}
