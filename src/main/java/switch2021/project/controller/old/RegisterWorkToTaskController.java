package switch2021.project.controller.old;

import switch2021.project.model.Sprint.Sprint;
import switch2021.project.repositories.SprintRepository;
import switch2021.project.model.Task.Task;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.model.valueObject.Date;
import switch2021.project.dto.old.TaskEffortDTO;
import switch2021.project.dto.old.TaskIdNameDTO;
import switch2021.project.dto.old.UserStorySprintProjectDTO;
import switch2021.project.mapper.old.RegisterWorkToTaskMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.repositories.old.ProjectStore;


import java.util.List;

public class RegisterWorkToTaskController {

    /**
     * Attributes
     **/
    private final Company company;
    private final RegisterWorkToTaskMapper mapper;
    private UserStory userStory;
    private List<Task> taskList;
    private Task task;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public RegisterWorkToTaskController(Company company, RegisterWorkToTaskMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

//TODO ver como vamos buscar lista de tasks nao vamos buscar a US
    /**
     * Methods
     **/
    public List<TaskIdNameDTO> getTasks(UserStorySprintProjectDTO userStorySprintProjectDTO) {
        String code = userStorySprintProjectDTO.getProjectCode();
        String sprintId = userStorySprintProjectDTO.getSprintId().toString();
        UserStoryID userStoryId = userStorySprintProjectDTO.getUserStoryId();

        ProjectStore projectStore = this.company.getProjectStore();
        Project project = projectStore.findById(code);

        SprintRepository sprintList = project.getSprintList();
        Sprint sprint = sprintList.findBySprintID(sprintId);

        this.userStory = sprint.getUsByIdFromScrumBoard(userStoryId);

//        this.taskList = this.userStory.getTasks().getTaskList();

        return this.mapper.toDtoList(this.taskList);
    }

    public TaskIdNameDTO getTask(int taskId) {
//        this.task = this.userStory.getTasks().getTaskById(taskId);
        return this.mapper.toDTO(this.task);
    }

    public void createTaskEffort(TaskEffortDTO taskEffortDTO) {

        int effortHours = taskEffortDTO.getEffortHours().getEffortHours();
        int effortMinutes = taskEffortDTO.getEffortMinutes().getEffortMinutes();
        Date effortDate = taskEffortDTO.getEffortDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();
        this.task.createAndSaveTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
//        return this.userStory.updateWorkDone(this.task);
    }
}
