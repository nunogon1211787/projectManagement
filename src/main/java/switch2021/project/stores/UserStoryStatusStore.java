package switch2021.project.stores;

import switch2021.project.model.UserStoryStatus;

import java.util.ArrayList;
import java.util.List;


public class UserStoryStatusStore {

    /**
     * Project Status Store Atributes
     * Contains a Project Status list
     **/

    private List<UserStoryStatus> userStoryStatusList;

    /**
     * Project Status Constructor
     **/

    public UserStoryStatusStore() {
        this.userStoryStatusList = new ArrayList<>();
    }

    public UserStoryStatusStore(List<UserStoryStatus> userStoryStatusList) {
        this.userStoryStatusList = userStoryStatusList;
    }

    public void populateDefault() {
        this.userStoryStatusList.add(new UserStoryStatus("To do"));
        this.userStoryStatusList.add(new UserStoryStatus("In progress"));
        this.userStoryStatusList.add(new UserStoryStatus("Done"));
        this.userStoryStatusList.add(new UserStoryStatus("Cancelled"));
        this.userStoryStatusList.add(new UserStoryStatus("In test"));
        this.userStoryStatusList.add(new UserStoryStatus("Refined"));
    }

    public List<UserStoryStatus> getUserStoryStatusList() {
        return userStoryStatusList;
    }

    public UserStoryStatus getUserStoryStatusByDescription(String description){
        UserStoryStatus userStoryStatus = null;
        for(UserStoryStatus uss : this.userStoryStatusList){
            if(uss.getDescription().equals(description)){
                userStoryStatus = uss;
            }
        }
        return userStoryStatus;
    }
    /**
     * Add UserStory Status
     * Adds a new UserStory Status object to the UserStory Status List
     **/

    public boolean add(UserStoryStatus userStoryStat) {
        this.userStoryStatusList.add(userStoryStat);
        return true;
    }

}
