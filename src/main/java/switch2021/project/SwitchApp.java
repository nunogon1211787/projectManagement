package switch2021.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import switch2021.project.repositories.SystemUserRepositoryInterface;
import switch2021.project.repositories.jpa.SystemUserJpa;

@SpringBootApplication
public class SwitchApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SwitchApp.class, args);
    }

    //populate with SystemUsers
    @Autowired
    private SystemUserRepositoryInterface sURepository;

    @Override
    public void run(String... args) throws Exception {
        this.sURepository.save(new SystemUserJpa("Ramesh Fadatare", "ramesh@gmail.com", "tester", "photo.png"));
        this.sURepository.save(new SystemUserJpa("Tom Cruise", "tom@gmail.com", "actor", "photo.png"));
        this.sURepository.save(new SystemUserJpa("Tony Stark", "tony@gmail.com", "tester", "photo.png"));
    }

    @Bean
    public CommandLineRunner app() {
        return (args) -> {

        };
    }
}
