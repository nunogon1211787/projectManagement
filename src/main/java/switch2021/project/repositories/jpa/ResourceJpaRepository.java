package switch2021.project.repositories.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.ResourceJpa;
import switch2021.project.model.Resource.ResourceIDReeng;

public interface ResourceJpaRepository extends JpaRepository<ResourceJpa, ResourceIDReeng> {

}
