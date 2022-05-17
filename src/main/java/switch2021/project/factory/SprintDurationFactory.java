package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.ISprintDurationFactory;
import switch2021.project.model.valueObject.SprintDuration;

@Component
public class SprintDurationFactory implements ISprintDurationFactory {
    @Override
    public SprintDuration create (int sprintDurationDays){
        return new SprintDuration(sprintDurationDays);
    }
}
