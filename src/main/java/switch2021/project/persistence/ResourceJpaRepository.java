package switch2021.project.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.jpa.ResourceIDJpa;
import switch2021.project.datamodel.jpa.ResourceJpa;

public interface ResourceJpaRepository extends JpaRepository<ResourceJpa, ResourceIDJpa> {

}
