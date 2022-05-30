package switch2021.project.presistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.UserJpa;


public interface SystemUserRepositoryInterface extends JpaRepository<UserJpa, String> {

}
