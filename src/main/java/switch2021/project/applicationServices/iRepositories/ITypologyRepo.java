package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import java.util.List;

public interface ITypologyRepo {

    /**
     * The repository should be able to find an object using given Identity.
     * @return
     */
    Typology findByTypologyId(TypologyID id);


    /**
     * Finds all objects from this repository.
     */
    List<Typology> findAll();


    /**
     * Save an object in the list of the repository.
     * @return
     */
    Typology save(Typology typology);


    /**
     * Check if the description already exists.
     */
    boolean existsByTypologyId(TypologyID id);


    /**
     * Delete the object from this repository.
     */
    void deleteByTypologyId(TypologyID id);
}
