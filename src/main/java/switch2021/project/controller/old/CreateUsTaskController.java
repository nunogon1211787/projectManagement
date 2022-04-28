package switch2021.project.controller.old;

import switch2021.project.dto.old.CreateTaskDTO;
import switch2021.project.mapper.old.TaskMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.Task.TaskStore;

import java.util.List;

public class CreateUsTaskController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private TaskStore taskStore;
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

        taskTypesNames = TaskTypeEnum.getTaskTypesDescriptionEnums();
        return taskTypesNames;
    }

//    public List<String> getUsTasks(String projCode, int sprintID){
//        this.project = this.company.getProjectStore().getProjectByCode(projCode);
//        UserStory userStory = this.project.getSprintList().findSprintById(sprintID).getUsByIdFromScrumBoard(usId);
////        this.taskStore = userStory.getTasks();
//
//        return this.taskStore.getTasksNames();
//    }

    public List<String> getCurrentProjectTeam(){
        List<String> currentResourcesNames;

        currentResourcesNames = this.project.getProjectTeam().getCurrentResourcesNames();
        return currentResourcesNames;
    }

    public boolean createUsTask(CreateTaskDTO createTaskDTO){
        return this.taskStore.createUsTask(createTaskDTO, this.taskMapper, this.project);
    }
}
