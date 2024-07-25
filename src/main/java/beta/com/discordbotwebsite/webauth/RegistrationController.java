package beta.com.discordbotwebsite.webauth;

import beta.com.discordbotwebsite.model.Address;
import beta.com.discordbotwebsite.model.ApproveStatus;
import beta.com.discordbotwebsite.model.Roles;
import beta.com.discordbotwebsite.util.JsonStringFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ObjectMapper objectMapper;

    public RegistrationController(final RegistrationService registrationService,
                                  final ObjectMapper objectMapper) {
        this.registrationService = registrationService;
        this.objectMapper = objectMapper;
    }

    @InitBinder
    public void jsonFormatting(final WebDataBinder binder) {
        binder.addCustomFormatter(new JsonStringFormatter<Address>(objectMapper) {
        }, "userAddress");
    }

    @GetMapping("/register")
    public String register(@ModelAttribute final RegistrationRequest registrationRequest) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid final RegistrationRequest registrationRequest,
                           final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        registrationService.register(registrationRequest);
        return "redirect:/login";
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("rolesValues", Roles.values());
        model.addAttribute("approveStatusValues", ApproveStatus.values());
    }

}
