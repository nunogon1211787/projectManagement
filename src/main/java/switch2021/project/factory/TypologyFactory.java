package switch2021.project.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IFactoryTypology;
import switch2021.project.factoryInterface.ITypologyIDFactory;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.TypologyID;

@Component
public class TypologyFactory implements IFactoryTypology {

    @Autowired
    private ITypologyIDFactory typoIDFact;

    public TypologyFactory(ITypologyIDFactory typoIDFact) {
        this.typoIDFact = typoIDFact;
    }

    @Override
    public Typology createTypology(TypologyDTO inputDto) {
        TypologyID id = typoIDFact.createId(inputDto);
        return new Typology(id);
    }
}
