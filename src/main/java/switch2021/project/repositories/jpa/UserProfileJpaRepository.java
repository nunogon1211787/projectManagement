package switch2021.project.repositories.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.datamodel.UserProfileJpa;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.List;
import java.util.Optional;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UserProfileID> {


    UserProfileJpa save(UserProfileJpa userProfileJpa);


    List<UserProfileJpa> findAll();

    Optional<UserProfileJpa> findById(UserProfileID userProfileID);

    boolean existsById(UserProfileID userProfileID);

    void deleteById(UserProfileID userProfileID);


}