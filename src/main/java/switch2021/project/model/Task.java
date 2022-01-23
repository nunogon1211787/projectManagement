package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

@Setter

@Getter

public class Task {

    private String description;

    public Task (String description){
        this.description = description;
    }

    public Task(){}
}
