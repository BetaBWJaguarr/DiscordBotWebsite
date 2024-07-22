package beta.com.discordbotwebsite.service;

import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.model.UserDTO;
import beta.com.discordbotwebsite.repos.UserRepository;
import beta.com.discordbotwebsite.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder,
            final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> userMapper.updateUserDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final UUID id) {
        return userRepository.findById(id)
                .map(user -> userMapper.updateUserDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final UserDTO userDTO) {
        final User user = new User();
        userMapper.updateUser(userDTO, user, passwordEncoder);
        return userRepository.save(user).getId();
    }

    public void update(final UUID id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        userMapper.updateUser(userDTO, user, passwordEncoder);
        userRepository.save(user);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

}
