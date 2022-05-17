package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.NumberOfSprints;

public interface INumberOfSprintsFactory {
    NumberOfSprints create (int numberOfSprints);
}
