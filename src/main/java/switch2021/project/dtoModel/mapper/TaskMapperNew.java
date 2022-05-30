package switch2021.project.dtoModel.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.entities.aggregates.Task.TaskReeng;

@Component
public class TaskMapperNew {

    public OutputTaskDTO model2Dto(TaskReeng task) {

        OutputTaskDTO taskDto = new OutputTaskDTO();

        taskDto.name = task.getIdTask().getTaskName().getText();
        taskDto.description = task.getDescription().getText();
        taskDto.effortEstimate = task.getEffortEstimate()+"";
        taskDto.type = task.getType().toString();
        taskDto.responsible = task.getResponsible().toString();

        return taskDto;
    }
}
