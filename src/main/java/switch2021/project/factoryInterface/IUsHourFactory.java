package switch2021.project.factoryInterface;


import switch2021.project.model.valueObject.UsHour;


public interface IUsHourFactory {
    UsHour create (double timeEstimate);
}
