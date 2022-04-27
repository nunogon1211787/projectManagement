package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.factory.TypologyFactory;
import switch2021.project.model.Typology.Typology;
import switch2021.project.repositories.TypologyRepository;

import java.util.List;
//import org.springframework.stereotype.Service;

@Service
public class TypologyService {

    /**
     * Attributes.
     */
    @Autowired
    private final TypologyRepository repository;
    private final TypologyFactory typologyFactory = new TypologyFactory();


    /**
     * Constructor.
     */
    public TypologyService(TypologyRepository typoRep) {
        this.repository = typoRep;
    }


    /**
     * Create and save a new typology in the repository.
     **/
    public boolean createAndSaveTypology(String description) {
        Typology newTypo = this.typologyFactory.createTypology(description);

        return this.repository.saveTypology(newTypo);
    }


//    /**
//     * Typology populator, that populates the typology repository with pre-set objects.
//     **/
//    public void populateDefault() {
//        createAndSaveTypology("Fixed Cost");
//        createAndSaveTypology("Time and Materials");
//    }



// verify if those methods is necessary <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


//    /**
//     * Methods to interact with the repository.
//     */
//    public Typology findTypologyByDescription(String description) {
//       return repository.findTypologyByDescription(description);
//    }
//
//    public List<Typology> findAllTypology() {
//        return repository.findAllTypology();
//    }
//
//    public void deleteTypology(String description) {
//        repository.deleteTypology(description);
//    }
}
