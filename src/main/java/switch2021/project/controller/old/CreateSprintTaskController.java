package switch2021.project.controller.old;

import switch2021.project.mapper.old.TaskMapper;
import switch2021.project.dto.old.CreateTaskDTO;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Task.TaskStore;
import switch2021.project.model.Task.TaskTypeEnum;


import java.util.List;

public class CreateSprintTaskController {

    private final Company company;
    private Project proj;
    private TaskStore taskStore;
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

        taskTypesNames = TaskTypeEnum.getTaskTypesDescriptionEnums();

        return taskTypesNames;
    }

//    public List<String> getSprintTasks(String projCode, String sprintID){
//
//        this.proj = this.company.getProjectStore().findById(projCode);
//
//        this.taskStore = this.proj.getSprintList().findSprintById(sprintID).getTaskStore();
//
//        return this.taskStore.getTasksNames();
//    }

    public List<String> getCurrentProjectTeam(){

        List<String> currentResourcesNames;

        currentResourcesNames = this.proj.getProjectTeam().getCurrentResourcesNames();

        return currentResourcesNames;
    }

    public boolean createSprintTask(CreateTaskDTO createTaskDTO){ return this.taskStore.createSprintTask(createTaskDTO, this.taskMapper, this.proj); }


}
