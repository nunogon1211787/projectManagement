package switch2021.project.factory;

import switch2021.project.factoryInterface.TypologyFactoryInterface;
import switch2021.project.model.Typology.Typology;

public class TypologyFactory implements TypologyFactoryInterface {
    @Override
    public Typology createTypology(String description) {
       return new Typology(description);
    }
}
