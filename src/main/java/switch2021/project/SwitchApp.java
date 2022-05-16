package switch2021.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import switch2021.project.service.TypologyService;

@SpringBootApplication
public class SwitchApp {

    public static void main(String[] args) {
        SpringApplication.run(SwitchApp.class, args);
    }

    @Bean
    public CommandLineRunner app() {
        return (args) -> {

        };
    }
}
