package beta.com.discordbotwebsite.webauth;

import beta.com.discordbotwebsite.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) boolean error,
                        @ModelAttribute("authentication") AuthenticationRequest authenticationRequest, Model model) {
        if (error) {
            String errorMessage = messageSource.getMessage("login.error", null, LocaleContextHolder.getLocale());
            model.addAttribute("errorMessage", errorMessage);
        }

        return "auth/login";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        return "auth/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        boolean emailSent = passwordResetService.sendPasswordResetEmail(email);
        if (emailSent) {
            model.addAttribute("successMessage",messageSource.getMessage("forgotPassword.success", null, LocaleContextHolder.getLocale()));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("forgotPassword.error", null, LocaleContextHolder.getLocale()));
        }
        return "auth/forgot-password";
    }
}
