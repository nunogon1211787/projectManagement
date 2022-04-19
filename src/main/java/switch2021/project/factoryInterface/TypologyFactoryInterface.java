package switch2021.project.factoryInterface;

import switch2021.project.model.Typology.Typology;

public interface TypologyFactoryInterface {

    Typology createTypology(String description);
}
