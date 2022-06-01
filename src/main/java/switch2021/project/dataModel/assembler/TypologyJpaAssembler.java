package switch2021.project.dataModel.assembler;

import org.springframework.stereotype.Service;
import switch2021.project.dataModel.jpa.TypologyJpa;
import switch2021.project.entities.aggregates.Typology.Typology;

@Service
public class TypologyJpaAssembler {

    public TypologyJpa toData (Typology typology) {
        return new TypologyJpa(typology.getId_description());
    }

    public Typology toDomain (TypologyJpa typologyJpa) {
        return new Typology(typologyJpa.getId());
    }
}
