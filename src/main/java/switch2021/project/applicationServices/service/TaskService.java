package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.dtoModel.mapper.TaskMapper;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.aggregates.UserStory.UserStory;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private ITaskFactory ITaskFactory;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ITaskRepo taskRepositoryInterface;
    @Autowired
    private IResourceRepo iResourceRepo;
    @Autowired
    private IResourceIDFactory resourceIDFactory;
    @Autowired
    private ISprintRepo iSprintRepo;
    @Autowired
    private IUserStoryRepo iUserStoryRepo;


    /**
     * Create and Save Task (US022)
     */
    public OutputTaskDTO createAndSaveTask(TaskDTO taskDTO) throws IllegalArgumentException {
        ResourceID resId = createResourceIdByStringInputFromController(taskDTO.responsible);
        TaskContainerID taskConId = returnTaskContainerID(taskDTO.taskContainerID);

        if(!iResourceRepo.existsById(resId)) {
            throw new IllegalArgumentException("This user is not associate to this project!");
        }
        Task newTask = ITaskFactory.createTask(taskDTO, resId, taskConId);
        Optional<Task> savedTask = taskRepositoryInterface.save(newTask);

        return savedTask.map(task -> taskMapper.toDto(task)).orElse(null);
    }


    /**
     *
     */
    private TaskContainerID returnTaskContainerID(String taskContainerID) {
        TaskContainerID z;

        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(new UserStoryID(taskContainerID));

        UserStory y = foundUs.flatMap(us -> foundUs).orElse(null);


        if(y != null) {
            z = y.getUserStoryID();
        } else {
            Optional<Sprint> x = iSprintRepo.findBySprintID(new SprintID(taskContainerID));
            if (x.isPresent()) {
                z = x.get().getSprintID();
            } else {
                throw new IllegalArgumentException("ID inválido");
            }
        }
        return z;
    }

    private ResourceID createResourceIdByStringInputFromController(String id) {
        String[] values = id.split("_");// user_project_startDate

        String sysUserID = values[0];
        String projID = values[1];
        String startDate = values[2];

        return resourceIDFactory.create(sysUserID, projID, startDate);
    }
}
