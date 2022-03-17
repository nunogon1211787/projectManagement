package switch2021.project.controller;

import switch2021.project.immutable.Date;
import switch2021.project.dto.TaskEffortDTO;
import switch2021.project.dto.TaskIdNameDTO;
import switch2021.project.dto.UserStorySprintProjectDTO;
import switch2021.project.mapper.RegisterWorkToTaskMapper;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;

import java.util.List;

public class RegisterWorkToTaskController {

    /**
     * Attributes
     **/

    private final Company company;
    private final RegisterWorkToTaskMapper mapper;
 //   private Project project;
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

        SprintList sprintList = project.getSprints();
        Sprint sprint = sprintList.getSprint(sprintId);

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

        int effortHours = taskEffortDTO.getEffort().getEffortHours();
        int effortMinutes = taskEffortDTO.getEffort().getEffortMinutes();
       Date effortDate = taskEffortDTO.getEffortDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();
        TaskEffort taskEffort = this.task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        this.task.saveTaskEffort(taskEffort);
        return this.userStory.updateWorkDone(this.task);
    }

}
