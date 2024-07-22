package beta.com.discordbotwebsite.service;

import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.model.AuthUserDetails;
import beta.com.discordbotwebsite.repos.UserRepository;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public AuthUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthUserDetails loadUserByUsername(final String username) {
        final User user = userRepository.findByEmailIgnoreCase(username);
        if (user == null) {
            log.warn("user not found: {}", username);
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        session.setAttribute("id", user.getId().toString());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("roles", user.getRoles().toString());
        final List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRoles().name()));
        return new AuthUserDetails(user.getId(), username, user.getPassword(), authorities);
    }
}
