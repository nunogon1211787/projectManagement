package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.model.Typology.Typology;

import java.util.List;

@Component
public interface IRepoTypology {

    List<Typology> getTypologyList();

    /**
     * The repository should be able to find an object using given Identity.
     */
    Typology findTypologyByDescription(String description);


    /**
     * Finds all objects from this repository.
     */
    List<Typology> findAllTypology();


    /**
     * Save an object in the list of the repository.
     */
    boolean saveTypology(Typology typology);


    /**
     * Check if the description already exists.
     */
    boolean existsByTypologyId(String description);

    /**
     * Delete the object from this repository.
     */
    void deleteTypology(String description);
}
