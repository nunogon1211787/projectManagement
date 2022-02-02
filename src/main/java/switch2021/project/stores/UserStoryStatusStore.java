package switch2021.project.stores;

import switch2021.project.model.UserStoryStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserStoryStatusStore {

    /**
     * Project Status Store Atributes
     * Contains a Project Status list
     **/

    private final List<UserStoryStatus> userStoryStatusList;

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
        userStoryStatusList.get(0).setSprintAvailable(true);
        this.userStoryStatusList.add(new UserStoryStatus("In progress"));
        userStoryStatusList.get(1).setSprintAvailable(true);
        this.userStoryStatusList.add(new UserStoryStatus("Done"));
        userStoryStatusList.get(2).setSprintAvailable(true);
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

//    public boolean add(UserStoryStatus userStoryStat) {
//        this.userStoryStatusList.add(userStoryStat);
//        return true;
//    }

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

    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStoryStatusStore)) return false;
        UserStoryStatusStore that = (UserStoryStatusStore) o;
        return Objects.equals(this.userStoryStatusList, that.userStoryStatusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryStatusList);
    }

}
