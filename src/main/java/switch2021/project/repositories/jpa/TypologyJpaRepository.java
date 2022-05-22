package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.TypologyJpa;
import switch2021.project.model.valueObject.TypologyID;

import java.util.List;
import java.util.Optional;

public interface TypologyJpaRepository extends JpaRepository<TypologyJpa, TypologyID> {

    TypologyJpa save(TypologyJpa typologyJpa);

    Optional<TypologyJpa> findById(TypologyID typologyID);

    boolean existsById(TypologyID typologyID);

    List<TypologyJpa> findAll();

    void deleteById(TypologyID typologyID);
}