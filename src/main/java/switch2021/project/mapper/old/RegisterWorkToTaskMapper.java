package switch2021.project.mapper.old;

import switch2021.project.dto.old.TaskIdNameDTO;
import switch2021.project.model.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class RegisterWorkToTaskMapper {

    /**
     * Method to change data in to a Task ID Name DTO
     **/
    public TaskIdNameDTO toDTO(Task task) {
        return new TaskIdNameDTO(task.getIdTask(), task.getName().getText());
    }


    /**
     * Method to change data in to a Task ID Name DTO List
     **/
    public List<TaskIdNameDTO> toDtoList(List<Task> taskList) {
        List<TaskIdNameDTO> taskIdNameDTOList = new ArrayList<>();

        for (Task i : taskList) {
            taskIdNameDTOList.add(toDTO(i));
        }
        return taskIdNameDTOList;
    }
}
