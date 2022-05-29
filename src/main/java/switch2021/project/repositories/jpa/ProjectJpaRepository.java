package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.ProjectJpa;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpa, String> {

//     List<ProjectJpa> findAll();
//
//    Optional<ProjectJpa> findById(ProjectID id);
//
//    boolean existsById(ProjectID id);
//
//    ProjectJpa save(ProjectJpa project);

}
