package switch2021.project.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.jpa.ResourceIDJpa;
import switch2021.project.dataModel.jpa.ResourceJpa;

public interface ResourceJpaRepository extends JpaRepository<ResourceJpa, ResourceIDJpa> {

}
