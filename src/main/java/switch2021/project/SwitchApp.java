package switch2021.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import switch2021.project.repositories.jpa.UserJpaRepository;
import switch2021.project.datamodel.UserJpa;

@SpringBootApplication
public class SwitchApp /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        SpringApplication.run(SwitchApp.class, args);
    }

//    @Autowired
//    private UserJpaRepository sURepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//    }

//    @Bean
//    public CommandLineRunner app() {
//        return (args) -> {
//            this.sURepository.save(new UserJpa("Ramesh Fadatare", "ramesh@gmail.com", "tester", "photo.png"));
//            this.sURepository.save(new UserJpa("Tom Cruise", "tom@gmail.com", "actor", "photo.png"));
//            this.sURepository.save(new UserJpa("Tony Stark", "tony@gmail.com", "tester", "photo.png"));
//
//        };
//    }
}
