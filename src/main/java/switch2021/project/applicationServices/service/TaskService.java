package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.dtoModel.dto.TaskEffortDTO;
import switch2021.project.dtoModel.mapper.TaskMapper;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEffortFactory;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.TaskEffort;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private ITaskFactory taskFactory;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ITaskRepo taskRepo;
    @Autowired
    private IResourceRepo resourceRepo;
    @Autowired
    private ISprintRepo sprintRepo;
    @Autowired
    private IUserStoryRepo userStoryRepo;
    @Autowired
    private IEffortFactory effortFactory;

    /**
     * Create and Save Task (US031)
     */
    public OutputTaskDTO createAndSaveTask(TaskDTO inputDTO) throws IllegalArgumentException {
        Task newTask = taskFactory.createTask(inputDTO);

        if (taskRepo.existsById(newTask.getTaskID().toString())) {
            throw new IllegalArgumentException("Task already exists.");
        }
        if (!resourceRepo.existsById(newTask.getResponsible())) {
            throw new IllegalArgumentException("This user is not associated to this project!");
        }
        TaskContainerID sprintOrUsID = newTask.getTaskID().getTaskContainerID();
        if (sprintOrUsID instanceof SprintID) {
            validateSprint(((SprintID) sprintOrUsID));
        }
        if (sprintOrUsID instanceof UserStoryID) {
            validateUserStory(((UserStoryID) sprintOrUsID));
        }

        Task savedTask = taskRepo.save(newTask);
        return taskMapper.toDto(savedTask);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OutputTaskDTO getTaskById(String taskID) {
        Optional<Task> optionalTask = taskRepo.findById(taskID);

        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        return taskMapper.toDto(optionalTask.get());
    }

    public CollectionModel<OutputTaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        CollectionModel<OutputTaskDTO> outputTaskDTOs = taskMapper.toCollectionDto(tasks);
        return CollectionModel.of(outputTaskDTOs);
    }

    public CollectionModel<OutputTaskDTO> getAllTasksByTaskContainerID(String taskContainerID) {
        List<Task> taskContainerIDTasks = taskRepo.findAllByTaskContainerID(taskContainerID);
        return taskMapper.toCollectionDto(taskContainerIDTasks);
    }

    public OutputTaskDTO createAndAddEffort(String taskID, TaskEffortDTO taskEffortDTO) throws IllegalArgumentException {
        Task task = getTask(taskID);
        TaskEffort taskEffort = effortFactory.createTaskEffort(taskEffortDTO);
        task.toAddEffort(taskEffort);

        Task updatedTask = taskRepo.save(task);
        return taskMapper.toDto(updatedTask);
    }

    private Task getTask(String taskID) {
        Optional<Task> optionalTask = taskRepo.findById(taskID);

        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        return optionalTask.get();
    }

    private void validateSprint(SprintID sprintID) {
        Optional<Sprint> optionalSprint = sprintRepo.findBySprintID(sprintID);
        Sprint sprint = optionalSprint.flatMap(sp -> optionalSprint).orElse(null);

        if (sprint == null) {
            throw new NullPointerException("Sprint doesn't exist");
        }
        if (sprint.getEndDate() != null) {
            throw new IllegalArgumentException("sprint is ended");
        }
    }

    private void validateUserStory (UserStoryID userStoryID){
        Optional<UserStory> optionalUserStory = userStoryRepo.findByUserStoryId(userStoryID);
        UserStory userStory = optionalUserStory.flatMap(us -> optionalUserStory).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }
        if(userStory.getUsEndDate()!=null){
            throw new IllegalArgumentException("User story is not open");
        }
    }
}
