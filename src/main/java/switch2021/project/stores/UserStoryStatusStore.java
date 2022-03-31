package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.valueObject.UserStoryStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserStoryStatusStore {

    /**
     * User Story Status Store Attributes (Contains a Project Status list)
     **/
    private final List<UserStoryStatus> userStoryStatusList;


    /**
     * User Story Status Constructor
     **/
    public UserStoryStatusStore() {
        this.userStoryStatusList = new ArrayList<>();
    }


    /**
     * Populate default for status
     **/
    public void populateDefault() {
        this.userStoryStatusList.add(new UserStoryStatus("To do", true));
        this.userStoryStatusList.add(new UserStoryStatus("In progress", true));
        this.userStoryStatusList.add(new UserStoryStatus("Done", true));
        this.userStoryStatusList.add(new UserStoryStatus("Cancelled", false));
        this.userStoryStatusList.add(new UserStoryStatus("In test", false));
        this.userStoryStatusList.add(new UserStoryStatus("Refined", false));
    }


    /**
     * Methods
     **/
    public UserStoryStatus getUserStoryStatusByDescription(String description) {
        UserStoryStatus userStoryStatus = null;
        for (UserStoryStatus uss : this.userStoryStatusList) {
            if (uss.getDescription().getText().equals(description)) {
                userStoryStatus = uss;
            }
        }
        return userStoryStatus;
    }

    public boolean checkUserStoryStatusExists(UserStoryStatus status) {

        for (UserStoryStatus st : userStoryStatusList) {
            if (st.equals(status)) {
                return true;
            }
        }
        return false;
    }

    public boolean saveNewUserStoryStatus(UserStoryStatus status) {
        boolean msg = false;
        if (!checkUserStoryStatusExists(status)) {
            this.userStoryStatusList.add(status);
            msg = true;
        }
        return msg;
    }
}
