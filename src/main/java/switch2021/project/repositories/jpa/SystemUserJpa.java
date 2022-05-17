package switch2021.project.repositories.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "systemUsers")
public class SystemUserJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String email;
    private String function;
    private String photo;

    public SystemUserJpa(String userName, String email, String function, String photo) {
        this.userName = userName;
        this.email = email;
        this.function = function;
        this.photo = photo;
    }
}