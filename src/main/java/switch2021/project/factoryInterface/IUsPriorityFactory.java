package switch2021.project.factoryInterface;


import switch2021.project.model.UserStory.UsPriority;


public interface IUsPriorityFactory {
    UsPriority create (int priority);
}
