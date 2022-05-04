package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.Typology.Typology;

@Component
public interface IFactoryTypology {
    Typology createTypology(TypologyDTO dto);
}
