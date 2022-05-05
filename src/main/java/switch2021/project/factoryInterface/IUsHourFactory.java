package switch2021.project.factoryInterface;


import switch2021.project.model.UserStory.UsHour;


public interface IUsHourFactory {
    UsHour create (double timeEstimate);
}
