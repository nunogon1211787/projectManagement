package switch2021.project.factoryInterface;

import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.Typology.Typology;

public interface ITypologyFactory {
    Typology createTypology(TypologyDTO inputDto);
}
