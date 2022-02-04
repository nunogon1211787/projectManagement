package switch2021.project.mapper;

import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.util.List;

public class TaskMapper {

    /**
     * Method to break a Create Task DTO data
     **/

    public Task toModel(CreateTaskDTO dto, Project proj){

        String name = dto.getName();
        String description = dto.getDescription();
        int effortEstimate = dto.getEffortEstimate();
        Resource responsible = proj.getProjectTeam().getResourceByName(dto.getResponsible());
        TaskType type = App.getInstance().getCompany().getTaskTypeStore().getTypeByName(dto.getTypeName());

        if(dto.getPrecedenceList() == null) {
            return new Task(name, description, effortEstimate, type, responsible);
        }
        else{
            List<String> precedenceList = dto.getPrecedenceList();
            return new Task(name, description, effortEstimate, type, responsible, precedenceList);
        }
    }
}
