package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.ITypologyIDFactory;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.TypologyID;

@Component
public class TypologyIDFactory implements ITypologyIDFactory {

    @Autowired
    private IDescriptionFactory descFact;

    @Override
    public TypologyID createId(TypologyDTO inputDto) {
        Description des = descFact.createDescription(inputDto.getDescription());
        return new TypologyID(des);
    }
}
