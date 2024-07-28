package beta.com.discordbotwebsite.config;

import static org.springframework.security.config.Customizer.withDefaults;

import beta.com.discordbotwebsite.model.Roles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
public class AuthSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // creates hashes with {bcrypt} prefix
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain authFilterChain(final HttpSecurity http,
                                               @Value("${auth.rememberMeKey}") final String rememberMeKey) throws Exception {
        return http.cors(withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/auth/css/**", "/auth/js/**", "/images/**", "/webjars/**", "/test", "/layout/css/**", "/forgot-password","/general/css/***").permitAll()
                        .requestMatchers("/users/**").hasAnyAuthority(Roles.ADMINISTRATOR.name(), Roles.USER.name())
                        .anyRequest().hasAuthority(Roles.ADMINISTRATOR.name()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("email"))
                .rememberMe(rememberMe -> rememberMe
                        .alwaysRemember(true)
                        .tokenValiditySeconds(((int) Duration.ofDays(180).getSeconds()))
                        .key(rememberMeKey))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logoutSuccess=true")
                        .deleteCookies("JSESSIONID"))
                .build();
    }
}