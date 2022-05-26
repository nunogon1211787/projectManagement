package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.ITypologyFactory;
import switch2021.project.factoryInterface.ITypologyIDFactory;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.mapper.TypologyMapper;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.TypologyID;

import java.util.List;
import java.util.Optional;

@Service
public class TypologyService {

    /**
     * Attributes.
     */
    @Autowired
    private ITypologyRepo iTypologyRepo;
    @Autowired
    private ITypologyFactory iTypologyFactory;
    @Autowired
    private ITypologyIDFactory factoryId;
    @Autowired
    private TypologyMapper mapper;

    /**
     *
     * Create and save a new typology in the repository.
     **/
    public TypologyDTO createAndSaveTypology(TypologyDTO inputDto) {
        Typology newTypo = this.iTypologyFactory.createTypology(inputDto);
        if(!iTypologyRepo.save(newTypo)) {
            throw new IllegalArgumentException("Typology already exists!");
        }
        return mapper.modelToDto(newTypo);
    }


    /**
     * Typology Find's Methods
     */
    public TypologyDTO findTypologyRequested(String id) {

        TypologyID typoId = factoryId.createId(new TypologyDTO(id));

        Optional<Typology> outputTypology = iTypologyRepo.findByTypologyId(typoId);

        if(outputTypology.isEmpty()){
            throw new IllegalArgumentException("Typology does not exist!");
        }
       return mapper.modelToDto(outputTypology.get());
    }

    public CollectionModel<TypologyDTO> findAllTypologies() {
        List<Typology> repositoryList = iTypologyRepo.findAll();

        if(repositoryList.isEmpty()) {
            throw new NullPointerException("Does not exists any Typology at this moment!");
        }

        return mapper.toCollectionModel(repositoryList);
    }


    /**
     * Typology Delete's Methods
     */
    public void deleteTypology(String id) {
        TypologyID typoId = factoryId.createId(new TypologyDTO(id));

        if (!iTypologyRepo.deleteByTypologyId(typoId)) {
            throw new IllegalArgumentException("Typology does not exists!");
        }
    }
}
