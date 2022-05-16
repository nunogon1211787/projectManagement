package switch2021.project.controller.old;

import switch2021.project.model.*;
import switch2021.project.model.UserProfile.UserProfile;
import java.util.List;

public class SearchSystemUsersController {

    /**
     * Attributes
     **/
    private final Company company;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public SearchSystemUsersController(Company company) {
        this.company = company;
    }


    /**
     * Getter method to receive an user profile list to send to UI
     **/
    public List<UserProfile> getUserProfileList() {
        return company.getUserProfileStore().findAllUserProfiles();
    }


    /*
     * Method to search users in the domain
     **/
/*    public List<SystemUser> searchUsers(String name, String email, String function, int state, String[] profileChoosenNameList) {
        List<UserProfile> profileChoosenList = new ArrayList<>();

        for (String s : profileChoosenNameList) {
            profileChoosenList.add(this.company.getUserProfileStore().findUserProfileByDescription(s));
        }
        return company.getSystemUserStore().searchUsers(name, email, function, state, profileChoosenList);
    }

 */
}
