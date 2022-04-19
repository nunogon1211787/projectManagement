package switch2021.project.mapper;

import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.Typology.Typology;

import java.util.ArrayList;
import java.util.List;

public class TypologyMapper {

    /**
     * Attributes
     */
    List<TypologyDTO> typologyDTOList;


    /**
     * Method to change data in to a Typology DTO.
     */
    public TypologyDTO toDTO(Typology typo) {
        return new TypologyDTO(typo.getId_description().getDescription().getText());
    }

    public List<TypologyDTO> toDTO(List<Typology> typologyList) {
        this.typologyDTOList = new ArrayList<>();

        for(Typology typo : typologyList) {
            TypologyDTO typoDTO = toDTO(typo);
            this.typologyDTOList.add(typoDTO);
        }
        return this.typologyDTOList;
    }
}
