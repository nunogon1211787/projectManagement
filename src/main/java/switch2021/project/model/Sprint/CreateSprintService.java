package switch2021.project.model.Sprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.factory.SprintFactory;

@Service
public class CreateSprintService {

    //Application Service

    /** Attributes */
    @Autowired
    private final SprintStore sprintStore;
    private final SprintFactory sprintFactory = new SprintFactory();

    /** Constructor */
    public CreateSprintService(SprintStore store) {this. sprintStore = store;}

    /** Create and Save a New Sprint */
    public Sprint createAndSaveSprint(String projectID, String sprintID, String name) {
        Sprint sprint = this.sprintFactory.createSprint(projectID, sprintID, name);
        this.sprintStore.saveSprint(sprint);
        return sprint;
    }



}
