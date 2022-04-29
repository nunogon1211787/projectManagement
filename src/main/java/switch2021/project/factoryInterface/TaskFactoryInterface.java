package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Task.TaskTypeEnum;

@Component
public interface TaskFactoryInterface {

    Task createTask(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible);

}
