package switch2021.project.stores;

import switch2021.project.model.UserStoryStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserStoryStatusStore {

    /**
     * Project Status Store Attributes (Contains a Project Status list)
     **/

    private final List<UserStoryStatus> userStoryStatusList;

    /**
     * Project Status Constructor
     **/

    public UserStoryStatusStore() {
        this.userStoryStatusList = new ArrayList<>();
    }

    public void populateDefault() {
        this.userStoryStatusList.add(new UserStoryStatus("To do"));
        userStoryStatusList.get(0).setSprintAvailable(true);
        this.userStoryStatusList.add(new UserStoryStatus("In progress"));
        userStoryStatusList.get(1).setSprintAvailable(true);
        this.userStoryStatusList.add(new UserStoryStatus("Done"));
        userStoryStatusList.get(2).setSprintAvailable(true);
        this.userStoryStatusList.add(new UserStoryStatus("Cancelled"));
        this.userStoryStatusList.add(new UserStoryStatus("In test"));
        this.userStoryStatusList.add(new UserStoryStatus("Refined"));
    }

    /**
     * Methods
     **/

    public List<UserStoryStatus> getUserStoryStatusList() {
        return Collections.unmodifiableList(userStoryStatusList);
    }

    public UserStoryStatus getUserStoryStatusByDescription(String description){
        UserStoryStatus userStoryStatus = null;
        for(UserStoryStatus uss : this.userStoryStatusList){
            if(uss.getDescription().getDescriptionF().equals(description)){
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
