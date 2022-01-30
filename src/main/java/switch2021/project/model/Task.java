package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.stores.TaskStatusStore;
import switch2021.project.utils.App;

import java.time.LocalDate;

@Setter
@Getter
public class Task {

    private String name;
    private String description;
    private TaskType type;
    private int effortEstimate;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private int workDone;
    private double percentExecution;
    private Resource responsible;

    public Task (String description){
        this.description = description;
        this.status = new TaskStatus("Planned");
    }

    public Task(String name, String description, int effortEstimate, TaskType type, Resource responsible){

        this.name = name;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.status = App.getInstance().getCompany().getTaskStatusStore().getInitialStatus();

    }
}
