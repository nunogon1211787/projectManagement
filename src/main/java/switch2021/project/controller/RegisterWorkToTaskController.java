package switch2021.project.controller;

import switch2021.project.dto.TaskEffortDTO;
import switch2021.project.dto.TaskIdNameDTO;
import switch2021.project.dto.UserStorySprintProjectDTO;
import switch2021.project.mapper.RegisterWorkToTaskMapper;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;
import switch2021.project.stores.TaskList;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class RegisterWorkToTaskController {

    /**
     * Attributes
     **/

    private final Company company;
    private RegisterWorkToTaskMapper mapper;
    private ProjectStore projectStore;
    private Project project;
    private Sprint sprint;
    private UserStory userStory;
    private List<Task> taskList;
    private Task task;
    private TaskIdNameDTO taskIdNameDTO;


    /**
     * Constructor to UI (with SINGLETON)
     **/

    /*public RegisterWorkToTaskController() {
        this.company = App.getInstance().getCompany();
    }*/

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

        this.projectStore = this.company.getProjectStore();
        this.project = this.projectStore.getProjectByCode(code);

        SprintList sprintList = this.project.getSprints();
        this.sprint = sprintList.getSprint(sprintId);

        SprintBacklog sprintBacklog = this.sprint.getSprintBacklog();
        this.userStory = sprintBacklog.getUserStory(userStoryId);

        TaskList taskList = this.userStory.getTasks();
        this.taskList = taskList.getTaskList();

        return this.mapper.toDtoList(this.taskList);
    }

    public TaskIdNameDTO getTask(int taskId) {
        TaskList taskList = this.userStory.getTasks();

        this.task = taskList.getTaskById(taskId);
        return this.mapper.toDTO(this.task);
    }

    public boolean createTaskEffort(TaskEffortDTO taskEffortDTO) {

        int effortHours = taskEffortDTO.getEffortHours();
        int effortMinutes = taskEffortDTO.getEffortMinutes();
        LocalDate effortDate = taskEffortDTO.getEffortDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();
        TaskEffort taskEffort = this.task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        this.task.saveTaskEffort(taskEffort);
        return this.userStory.updateWorkDone(this.task);
    }

}
