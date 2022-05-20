package switch2021.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.SystemUserJpa;

//for webApp demo testing
@Repository
public interface SystemUserRepositoryInterface extends JpaRepository<SystemUserJpa, Long> {

}
