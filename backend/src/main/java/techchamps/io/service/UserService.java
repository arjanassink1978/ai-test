package techchamps.io.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techchamps.io.repository.UserRepository;
import techchamps.io.model.User;
import techchamps.io.model.Role;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(String email, String username, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public void createAdminIfNotExists() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@test.nl");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
} 