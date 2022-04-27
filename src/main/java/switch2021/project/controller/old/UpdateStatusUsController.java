package switch2021.project.controller.old;

import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.repositories.ProjectStore;
import switch2021.project.model.valueObject.UserStoryStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateStatusUsController {

    /**
     * Attributes
     **/
    private final Company company;


    /**
     * Constructor (without SINGLETON)
     **/
    public UpdateStatusUsController(Company company) {
        this.company = company;
    }


    /**
     * Methods
     **/
    public List<String> getUsAvailableStatusList() {
        List<UserStoryStatus> statusList = this.company.getUserStoryStatusStore().getUserStoryStatusList();
        List<String> usList = new ArrayList<>();
        for (UserStoryStatus userStoryStatus : statusList) {
            if (userStoryStatus.isSprintAvailable())
                usList.add(userStoryStatus.getDescription().getText());
        }
        return Collections.unmodifiableList(usList);
    }
    //TODO CDC ver metodo changestatus tendo em conta que US nao tem status

//    public boolean changeStatusOfUs(String code, int usId, String userST) {
//        UserStoryStatus userStoryStatus = this.company.getUserStoryStatusStore().getUserStoryStatusByDescription(userST);
//        ProjectStore projectStore = company.getProjectStore();
//        Project project = projectStore.getProjectByCode(code);
//        Sprint sprint = project.getSprintList().getCurrentSprint();
//        UserStory userStory = sprint.getUsById(usId);
//        return userStory.setUserStoryStatusBoolean(userStoryStatus);
//    }
}
