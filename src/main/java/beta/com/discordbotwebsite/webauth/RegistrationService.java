package beta.com.discordbotwebsite.webauth;

import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.model.ApproveStatus;
import beta.com.discordbotwebsite.model.Roles;
import beta.com.discordbotwebsite.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(final RegistrationRequest registrationRequest) {
        log.info("registering new user: {}", registrationRequest.getEmail());

        final User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setPhonenumber(registrationRequest.getPhonenumber());
        user.setDiscordid(registrationRequest.getDiscordid());
        user.setUserAddress(registrationRequest.getUserAddress());
        user.setRoles(Roles.USER);
        user.setApproveStatus(ApproveStatus.PENDING);
        user.setStatus(false);
        userRepository.save(user);
    }

}