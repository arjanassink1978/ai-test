package techchamps.io.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import techchamps.io.service.UserService;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> userService.createAdminIfNotExists();
    }
} 