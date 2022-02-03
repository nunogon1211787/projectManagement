package switch2021.project.mapper;

import switch2021.project.dto.TaskIdNameDTO;
import switch2021.project.model.Task;

import java.util.ArrayList;
import java.util.List;

public class RegisterWorkToTaskMapper {

    public TaskIdNameDTO toDTO (Task task) {
        return new TaskIdNameDTO(task.getIdTask(), task.getName());
    }

    public List<TaskIdNameDTO> toDtoList(List<Task> taskList){
        List<TaskIdNameDTO> taskIdNameDTOList = new ArrayList<>();

        for(Task i: taskList) {
            taskIdNameDTOList.add(toDTO(i));
        }
        return taskIdNameDTOList;
    }
}
