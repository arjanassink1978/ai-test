package techchamps.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "techchamps.io")
@EnableJpaRepositories(basePackages = "techchamps.io.repository")
@EntityScan(basePackages = "techchamps.io.model")
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
