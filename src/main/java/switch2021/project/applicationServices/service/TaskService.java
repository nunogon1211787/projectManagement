package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.dtoModel.mapper.TaskMapper;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TaskID;

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

    /**
     * Create and Save Task (US031)
     */
    public OutputTaskDTO createAndSaveTask(TaskDTO inputDTO) throws IllegalArgumentException {
        if (taskRepo.existsById(new Description(inputDTO.taskName))) {
            throw new IllegalArgumentException("Task already exist.");
        }
        Task newTask = taskFactory.createTask(inputDTO);
        if(!resourceRepo.existsById(newTask.getResponsible())) {
            throw new IllegalArgumentException("This user is not associated to this project!");
        }
        Task savedTask = taskRepo.save(newTask);
        return taskMapper.toDto(savedTask);
    }

    public OutputTaskDTO getTaskById(String id) {
        TaskID taskID = taskMapper.stringToId(id);
        Optional<Task> optionalTask = taskRepo.findById(taskID.getTaskName());

        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        return taskMapper.toDto(optionalTask.get());
    }

    public CollectionModel<OutputTaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        CollectionModel<OutputTaskDTO> outputTaskDTOs= taskMapper.toCollectionDto(tasks);
        return CollectionModel.of(outputTaskDTOs);
    }

}
