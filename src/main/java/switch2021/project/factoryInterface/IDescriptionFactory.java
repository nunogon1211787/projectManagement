package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.Description;

public interface IDescriptionFactory {
    Description createDescription(String description);
}