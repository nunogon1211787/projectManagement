package switch2021.project.controller;

import switch2021.project.mapper.TaskMapper;
import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.model.*;
import switch2021.project.stores.*;


import java.util.List;

public class CreateSprintTaskController {

    private final Company company;
    private Project proj;
    private TaskList taskList;
    private final TaskMapper taskMapper;


    /**
     * Constructor to test (without SINGLETON)
     */

    public CreateSprintTaskController(Company company, TaskMapper taskMapper) {
        this.company = company;
        this.taskMapper = taskMapper;
    }

    /**
     * Methods to implement the feature
     */

    public List<String> getTaskTypes(){

        List<String> taskTypesNames;

        taskTypesNames = this.company.getTaskTypeStore().getTaskTypesNames();

        return taskTypesNames;
    }

    public List<String> getSprintTasks(String projCode, int sprintID){

        this.proj = this.company.getProjectStore().getProjectByCode(projCode);

        this.taskList = this.proj.getSprints().getSprint(sprintID).getTaskList();

        return this.taskList.getTasksNames();
    }

    public List<String> getCurrentProjectTeam(){

        List<String> currentResourcesNames;

        currentResourcesNames = this.proj.getProjectTeam().getCurrentResourcesNames();

        return currentResourcesNames;
    }

    public boolean createSprintTask(CreateTaskDTO createTaskDTO){ return this.taskList.createSprintTask(createTaskDTO, this.taskMapper, this.proj); }


}
