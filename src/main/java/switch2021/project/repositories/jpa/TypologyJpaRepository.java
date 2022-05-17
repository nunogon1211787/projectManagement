package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.TypologyJpa;
import switch2021.project.model.valueObject.TypologyID;

import java.util.List;
import java.util.Optional;

public interface TypologyJpaRepository extends JpaRepository<TypologyJpa, TypologyID> {

    public TypologyJpa save(TypologyJpa typologyJpa);

    public Optional<TypologyJpa> findById(TypologyID typologyID);

    public boolean existsById(TypologyID typologyID);

    public List<TypologyJpa> findAll();

    public void deleteById(TypologyID typologyID);
}
