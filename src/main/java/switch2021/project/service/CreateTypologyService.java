package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IFactoryTypology;
import switch2021.project.interfaces.IRepoTypology;
import switch2021.project.mapper.old.TypologyMapper;
import switch2021.project.model.Typology.Typology;

import java.util.List;

@Service
public class CreateTypologyService {

    /**
     * Attributes.
     */
//    @Autowired
    private IRepoTypology iRepoTypology;
//    @Autowired
    private IFactoryTypology iFactoryTypology;
    @Autowired
    private TypologyMapper mapper;

    /**
     * Create and save a new typology in the repository.
     **/
    public TypologyDTO createAndSaveTypology(TypologyDTO inputDto) {
        Typology newTypo = this.iFactoryTypology.createTypology(inputDto);
        this.iRepoTypology.saveTypology(newTypo);
        return mapper.modelToDto(newTypo);
    }


//    /**
//     * Typology populator, that populates the typology repository with pre-set objects.
//     **/
//    public void populateDefault() {
//        iRepoTypology.saveTypology(iFactoryTypology.createTypology("Fixed Cost"));
//        iRepoTypology.saveTypology(iFactoryTypology.createTypology("Time and Materials"));
//    } >>>>>>>>>>>>>> Needs to define where this method will be or if it needs this method <<<<<<<<<<<<<<<<<<


    /**
     * Typology Find's Methods
     */
    public TypologyDTO findTypologyByDescription(String description) {
        Typology outputTypology = iRepoTypology.findTypologyByDescription(description);
       return mapper.modelToDto(outputTypology);
    }

    public List<TypologyDTO> findAllTypology() {
        List<Typology> repositoryList = iRepoTypology.findAllTypology();
        return mapper.modelToDto(repositoryList);
    }


    /**
     * Typology Delete's Methods
     */
    public void deleteTypology(String description) {
        iRepoTypology.deleteTypology(description);
    }
}
