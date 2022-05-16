package switch2021.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.TypologyJpa;
import switch2021.project.model.valueObject.TypologyID;

import java.util.List;
import java.util.Optional;

public interface TypologyJpaRepository extends JpaRepository<TypologyJpa, TypologyID> {

    @Override
    default <S extends TypologyJpa> S save(S s) {
        return null;
    }

    @Override
    default Optional<TypologyJpa> findById(TypologyID typologyID) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(TypologyID typologyID) {
        return false;
    }

    @Override
    default List<TypologyJpa> findAll() {
        return null;
    }

    @Override
    default void deleteById(TypologyID typologyID) {

    }
}
