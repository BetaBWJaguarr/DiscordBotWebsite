package beta.com.discordbotwebsite.config;


import beta.com.discordbotwebsite.langmanager.LangManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LangManager langManager;

    public WebConfig(LangManager langManager) {
        this.langManager = langManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(langManager.localeChangeInterceptor());
    }
}
