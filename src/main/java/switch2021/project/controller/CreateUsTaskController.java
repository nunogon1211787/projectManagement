package switch2021.project.controller;

import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.mapper.TaskMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.stores.TaskList;

import java.util.List;

public class CreateUsTaskController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private TaskList taskList;
    private final TaskMapper taskMapper;


    /**
     * Methodds
     **/
    public CreateUsTaskController(Company company, TaskMapper taskMapper) {
        this.company = company;
        this.taskMapper = taskMapper;
    }

    public List<String> getTaskTypes(){
        List<String> taskTypesNames;

        taskTypesNames = this.company.getTaskTypeStore().getTaskTypesDescription();
        return taskTypesNames;
    }

    public List<String> getUsTasks(String projCode, int sprintID, int usId){
        this.project = this.company.getProjectStore().getProjectByCode(projCode);
        UserStory userStory = this.project.getSprintList().getSprintById(sprintID).getSprintBacklog().getUserStory(usId);
        this.taskList = userStory.getTasks();

        return this.taskList.getTasksNames();
    }

    public List<String> getCurrentProjectTeam(){
        List<String> currentResourcesNames;

        currentResourcesNames = this.project.getProjectTeam().getCurrentResourcesNames();
        return currentResourcesNames;
    }

    public boolean createUsTask(CreateTaskDTO createTaskDTO){
        return this.taskList.createUsTask(createTaskDTO, this.taskMapper, this.project);
    }
}
