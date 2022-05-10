package switch2021.project.factoryInterface;

import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.valueObject.TypologyID;

public interface ITypologyIDFactory {
    TypologyID createId(TypologyDTO inputDto);
}
