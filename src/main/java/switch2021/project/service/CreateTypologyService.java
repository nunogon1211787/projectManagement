package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IFactoryTypology;
import switch2021.project.interfaces.IRepoTypology;
import switch2021.project.mapper.TypologyMapper;
import switch2021.project.model.Typology.Typology;

import java.util.List;

@Service
public class CreateTypologyService {

    /**
     * Attributes.
     */
    @Autowired
    private IRepoTypology iRepoTypology;
    @Autowired
    private IFactoryTypology iFactoryTypology;
    @Autowired
    private TypologyMapper mapper;

    /**
     *
     * Create and save a new typology in the repository.
     **/
    public TypologyDTO createAndSaveTypology(TypologyDTO inputDto) {
        Typology newTypo = this.iFactoryTypology.createTypology(inputDto);
        if(!iRepoTypology.saveTypology(newTypo)) {
            throw new IllegalArgumentException("Typology already exists!");
        }
        return mapper.modelToDto(newTypo);
    }


    /**
     * Typology Find's Methods
     */
    public TypologyDTO findTypologyByDescription(TypologyDTO inputDto) {
        Typology outputTypology = iRepoTypology.findTypologyById(inputDto.description);
       return mapper.modelToDto(outputTypology);
    }

    public List<TypologyDTO> findAllTypologies() {
        List<Typology> repositoryList = iRepoTypology.findAllTypology();

        if(repositoryList.isEmpty()) {
            throw new NullPointerException("Does not exists any Typology at this moment!");
        }
        return mapper.modelToDto(repositoryList);
    }


    /**
     * Typology Delete's Methods
     */
    public void deleteTypology(TypologyDTO inputDto) {
        if (!iRepoTypology.existsByTypologyId(inputDto.getDescription())) {
            throw new IllegalArgumentException("Typology does not exists!");
        } else {
            iRepoTypology.deleteTypology(inputDto.description);
        }
    }
}
