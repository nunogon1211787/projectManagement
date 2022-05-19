package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.UserStoryJpa;
import switch2021.project.model.valueObject.UserStoryID;

public interface UserStoryJpaRepository extends JpaRepository<UserStoryJpa, UserStoryID> {

}
