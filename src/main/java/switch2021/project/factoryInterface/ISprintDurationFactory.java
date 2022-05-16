package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.SprintDuration;

public interface ISprintDurationFactory {
    SprintDuration create(int sprintDurationDays);
}
