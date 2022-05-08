package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.Typology.Typology;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypologyMapper {

    /**
     * Attributes
     */
    List<TypologyDTO> typologyDTOList;


    /**
     * Methods to change data in to a Typology DTO.
     */
    public TypologyDTO modelToDto(Typology typology) {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.description = typology.getId_description().getDescription().getText();
        return typologyDTO;
    }

    public List<TypologyDTO> modelToDto(List<Typology> typologyList) {
        this.typologyDTOList = new ArrayList<>();

        for(Typology typo : typologyList) {
            TypologyDTO typoDTO = modelToDto(typo);
            this.typologyDTOList.add(typoDTO);
        }
        return this.typologyDTOList;
    }
}
