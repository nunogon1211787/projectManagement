package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.ITypologyFactory;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.mapper.TypologyMapper;
import switch2021.project.model.Typology.Typology;

import java.util.List;

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
    private TypologyMapper mapper;

    /**
     *
     * Create and save a new typology in the repository.
     **/
    public TypologyDTO createAndSaveTypology(TypologyDTO inputDto) {
        Typology newTypo = this.iTypologyFactory.createTypology(inputDto);
        if(!iTypologyRepo.saveTypology(newTypo)) {
            throw new IllegalArgumentException("Typology already exists!");
        }
        return mapper.modelToDto(newTypo);
    }


    /**
     * Typology Find's Methods
     */
    public TypologyDTO findTypologyByDescription(TypologyDTO inputDto) {
        Typology outputTypology = iTypologyRepo.findTypologyById(inputDto.description);

        if(outputTypology == null){
            throw new IllegalArgumentException("This Typology does not exist!");
        }
       return mapper.modelToDto(outputTypology);
    }

    public List<TypologyDTO> findAllTypologies() {
        List<Typology> repositoryList = iTypologyRepo.findAllTypology();

        if(repositoryList.isEmpty()) {
            throw new NullPointerException("Does not exists any Typology at this moment!");
        }
        return mapper.modelToDto(repositoryList);
    }


    /**
     * Typology Delete's Methods
     */
    public void deleteTypology(TypologyDTO inputDto) {
        if (!iTypologyRepo.existsByTypologyId(inputDto.getDescription())) {
            throw new IllegalArgumentException("Typology does not exists!");
        } else {
            iTypologyRepo.deleteTypology(inputDto.description);
        }
    }
}
