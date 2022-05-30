package switch2021.project.interfaceAdapters.repositories;


import lombok.Getter;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskRepositoryInterface;
import switch2021.project.entities.aggregates.Task.TaskReeng;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class TaskRepository implements TaskRepositoryInterface {

    /*** Class Attributes **/
    private final List<TaskReeng> taskList;


    public TaskRepository(){
        this.taskList = new ArrayList<>();
    }

    /*** Class Methods **/

    public TaskReeng save(TaskReeng newTask) {
        if(newTask == null) {
            throw new IllegalArgumentException("Error: Task is null!");
        }

        if (existById(newTask.getIdTask().toString())) {
            throw new IllegalArgumentException("Error: Task already exists!");
        }

        taskList.add(newTask);

        return newTask;
    }

    public List<TaskReeng> findAll() {
        return new ArrayList<>(this.taskList);
    }

    public TaskReeng findById(String code) {
        TaskReeng task = null;
        for (TaskReeng task1 : taskList) {
            if(task1.getIdTask().toString().equals(code)){
                task = task1;
                break;
            }
        }
        return task;
    }

    public boolean existById(String id) {
        for (TaskReeng task : taskList) {
            if (task.getIdTask().toString().equals(id)) {
                return true;
            }
        }
        return false;
    }


}
