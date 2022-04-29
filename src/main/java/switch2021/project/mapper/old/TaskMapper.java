package switch2021.project.mapper.old;

import switch2021.project.dto.old.CreateTaskDTO;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Task.TaskTypeEnum;

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
        TaskTypeEnum type = dto.getTypeName();

        if(dto.getPrecedenceList() == null) {
            return new Task(name, description, effortEstimate, type, responsible);
        }
        else {
            List<String> precedenceList = dto.getPrecedenceList();
            return new Task(name, description, effortEstimate, type, responsible, precedenceList);
        }
    }
}
