package switch2021.project.controller;

import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintBacklog;
import switch2021.project.model.Task.Task;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.Date;
import switch2021.project.dto.TaskEffortDTO;
import switch2021.project.dto.TaskIdNameDTO;
import switch2021.project.dto.UserStorySprintProjectDTO;
import switch2021.project.mapper.RegisterWorkToTaskMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;

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


    /**
     * Methods
     **/
    public List<TaskIdNameDTO> getTasks(UserStorySprintProjectDTO userStorySprintProjectDTO) {
        String code = userStorySprintProjectDTO.getProjectCode();
        int sprintId = userStorySprintProjectDTO.getSprintId();
        int userStoryId = userStorySprintProjectDTO.getUserStoryId();

        ProjectStore projectStore = this.company.getProjectStore();
        Project project = projectStore.getProjectByCode(code);

        SprintList sprintList = project.getSprintList();
        Sprint sprint = sprintList.getSprintById(sprintId);

        SprintBacklog sprintBacklog = sprint.getSprintBacklog();
        this.userStory = sprintBacklog.getUserStory(userStoryId);

        this.taskList = this.userStory.getTasks().getTaskList();

        return this.mapper.toDtoList(this.taskList);
    }

    public TaskIdNameDTO getTask(int taskId) {
        this.task = this.userStory.getTasks().getTaskById(taskId);
        return this.mapper.toDTO(this.task);
    }

    public boolean createTaskEffort(TaskEffortDTO taskEffortDTO) {

        int effortHours = taskEffortDTO.getEffortHours().getEffortHours();
        int effortMinutes = taskEffortDTO.getEffortMinutes().getEffortMinutes();
        Date effortDate = taskEffortDTO.getEffortDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();
        this.task.createAndSaveTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        return this.userStory.updateWorkDone(this.task);
    }
}
