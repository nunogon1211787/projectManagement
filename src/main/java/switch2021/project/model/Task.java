package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.stores.TaskStatusStore;

@Setter
@Getter
public class Task {

    private String description;
    private TaskStatus status;
    /*private TaskList type;*/

    public Task (String description){
        this.description = description;
        this.status = new TaskStatus("Planned");
    }

    public Task(){}
}
