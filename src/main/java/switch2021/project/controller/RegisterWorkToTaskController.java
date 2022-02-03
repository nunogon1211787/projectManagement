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
    private Company company;
    private RegisterWorkToTaskMapper mapper;
    private ProjectStore projectStore;
    private Project project;
    private Sprint sprint;
    private UserStory userStory;
    private List<Task> taskList;
    private Task task;
    private TaskIdNameDTO taskIdNameDTO;

    // if not using DTO
    /*private Company company;
    private ProjectStore projectStore;
    private List<Project> currentProjectListByUser;
    private Project project;
    private List<Sprint> sprintList;
    private Sprint sprint;
    private List<UserStory> userStoryList;
    private UserStory userStory;
    private List<Task> taskList;
    private Task task;*/

    /**
     * Constructor to UI (with SINGLETON).
     */
    public RegisterWorkToTaskController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public RegisterWorkToTaskController(Company company, RegisterWorkToTaskMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

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

    public Task getTask(int taskId) {
        TaskList taskList = this.userStory.getTasks();

        this.task = taskList.getTaskById(taskId);
        return this.task;
    }

    public boolean createTaskEffort(TaskEffortDTO taskEffortDTO) {
        this.taskIdNameDTO = taskEffortDTO.getTaskIdNameDTO();
        int effortHours = taskEffortDTO.getEffortHours();
        int effortMinutes = taskEffortDTO.getEffortMinutes();
        LocalDate effortDate = taskEffortDTO.getEffortDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();

        int taskId = taskIdNameDTO.getTaskId();
        TaskList taskList = this.userStory.getTasks();
        this.task = taskList.getTaskById(taskId);

        TaskEffort taskEffort = this.task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        this.task.saveTaskEffort(taskEffort);
        return this.userStory.updateWorkDone(this.task);
    }


//Pode vir a dar jeito
    /*public List<ProjectCodeDTO> getCurrentProjectsByUserEmail(String email) {
        this.projStore = this.company.getProjectStore();
        List<Project> currentProjectList = projStore.getCurrentProjectsByUserEmail(email);

        this.projectCodeDTOList = this.mapper.projectListToProjectCodeDTOList(currentProjectList);
        return this.projectCodeDTOList;
    }

    public Project getProjectByCodeDTO(String code) {
        this.project = this.projStore.getProjectByCode(code);

        //this.projCodeDTO = this.projectCodeDTOList.getProjectCodeDTO(projCodeDTO);

        return this.project;
    }


    public List<SprintIdDTO> getProjectSprints(ProjectCodeDTO projCodeDTO) {
        this.projCodeDTO = this.projectCodeDTOList.getProjectCodeDTO(projCodeDTO);

        String code = this.mapper.projectCodeDTOToProjectCode(projCodeDTO);
        Project proj = company.getProjectStore().getProjectByCode(code);
        List<Sprint> sprintList = proj.getSprints().getSprintList();
        this.sprintIdDTOList = this.mapper.sprintListToSprintIdDTOList(sprintList);

        return this.sprintIdDTOList;
    }

    public UserStory getUserStory(int idUserStory) {
        this.userStoryParent = this.productBacklog.getUserStoryById(idUserStory);
        return userStoryParent;
    }*/


    //if not using DTO
    /*public List<Project> getCurrentProjectsByUserEmail(String email) {
        this.projectStore = this.company.getProjectStore();

        this.currentProjectListByUser = projectStore.getCurrentProjectsByUserEmail(email);
        return this.currentProjectListByUser;
    }

    public Project getProjectByCode(String code) {
        this.project = this.projectStore.getProjectByCode(code);
        return project;
    }

    public List<Sprint> getSprints() {
        SprintList sprintList = this.project.getSprints();

        this.sprintList = sprintList.getSprintList();
        return this.sprintList;
    }

    public Sprint getSprint(int sprintId) {
        SprintList sprintList = this.project.getSprints();

        this.sprint = sprintList.getSprint(sprintId);
        return this.sprint;
    }

    public List<UserStory> getUserStoryList() {
        SprintBacklog sprintBacklog = this.sprint.getSprintBacklog();

        this.userStoryList = sprintBacklog.getUserStoryList();
        return this.userStoryList;
    }

    public UserStory getUserStory(int userStoryId) {
        SprintBacklog sprintBacklog = this.sprint.getSprintBacklog();

        this.userStory = sprintBacklog.getUserStory(userStoryId);
        return this.userStory;
    }

    public List<Task> taskList() {
        TaskList taskList = this.userStory.getTasks();

        this.taskList = taskList.getTaskList();
        return this.taskList;
    }

    public Task getTask(int taskId) {
        TaskList taskList = this.userStory.getTasks();

        this.task = taskList.getTaskById(taskId);
        return this.task;
    }

    public boolean createTaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        TaskEffort taskEffort = this.task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        this.task.saveTaskEffort(taskEffort);
        return this.userStory.updateWorkDone(this.task);
    }*/
}
