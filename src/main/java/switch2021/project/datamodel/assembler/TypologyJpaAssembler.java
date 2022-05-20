package switch2021.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switch2021.project.datamodel.TypologyJpa;
import switch2021.project.model.Typology.Typology;

@Service
public class TypologyJpaAssembler {

    public TypologyJpa toData (Typology typology) {
        return new TypologyJpa(typology.getId_description());
    }

    public Typology toDomain (TypologyJpa typologyJpa) {
        return new Typology(typologyJpa.getId());
    }
}
