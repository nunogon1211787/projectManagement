package switch2021.project.utils;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RepositoryTemplate extends CrudRepository<Object, Long> {

    /**
     * The repository should be able to find an object using given Identity.
     */
    //Object findObjectById(Id any);


    /**
     * Finds all objects from this repository.
     */
    //List<Object> findAll();

    /**
     * Save a object in the list of the repository.
     */
    //boolean save(Object any);

}
