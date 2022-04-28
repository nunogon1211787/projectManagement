package switch2021.project.mapper;


import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputTaskDTO;
import switch2021.project.model.Task.Task;


@Component
public class TaskMapperNew {

    public OutputTaskDTO model2Dto(Task task) {

        OutputTaskDTO taskDto = new OutputTaskDTO();

        taskDto.name = task.getName().getNameF();
        taskDto.description = task.getDescription().getText();
        taskDto.effortEstimate = task.getEffortEstimate()+"";
        taskDto.type = task.getType().toString();
        taskDto.responsible = task.getResponsible().toString();

        return taskDto;
    }
}
