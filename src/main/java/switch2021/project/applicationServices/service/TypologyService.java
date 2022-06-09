package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.factories.factoryInterfaces.ITypologyFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITypologyIDFactory;
import switch2021.project.dtoModel.mapper.TypologyMapper;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;

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
        if(iTypologyRepo.existsByTypologyId(newTypo.getId_description())) {
            throw new IllegalArgumentException("Typology already exists!");
        }
        Typology saved = iTypologyRepo.save(newTypo);
        return mapper.modelToDto(saved);
    }


    /**
     * Typology Find's Methods
     */
    public TypologyDTO findTypologyRequested(String id) {
        TypologyID typoId = factoryId.createId(new TypologyDTO(id));

        Typology outputTypology = iTypologyRepo.findByTypologyId(typoId);

       return mapper.modelToDto(outputTypology);
    }

    public CollectionModel<TypologyDTO> findAllTypologies() {
        List<Typology> repositoryList = iTypologyRepo.findAll();

        return mapper.toCollectionModel(repositoryList);
    }


    /**
     * Typology Delete's Methods
     */
    public void deleteTypology(String id) {
        TypologyID typoId = factoryId.createId(new TypologyDTO(id));
        iTypologyRepo.deleteByTypologyId(typoId);
    }
}
