package switch2021.project.Mapper;

import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.model.*;
import switch2021.project.utils.App;

public class TaskMapper {

    public Task toModel(CreateTaskDTO dto, Project proj){

        String name = dto.getName();
        String description = dto.getDescription();
        int effortEstimate = dto.getEffortEstimate();
        Resource responsible = proj.getProjectTeam().getCurrentResourceByName(dto.getResponsible());
        TaskType type = App.getInstance().getCompany().getTaskTypeStore().getTypeByName(dto.getTypeName());

        return new Task(name, description, effortEstimate, type, responsible);

    }
}
