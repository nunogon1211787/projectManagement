package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.UserStoryJpa;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

public interface UserStoryJpaRepository extends JpaRepository<UserStoryJpa, UserStoryID> {

}
