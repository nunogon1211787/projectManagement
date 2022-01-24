package switch2021.project.stores;

import switch2021.project.model.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserProfileStore {

    /**
     * UserProfile Store Attributes. Contains a UserProfile list.
     **/
    private List<UserProfile> userProfileList;

    /**
     * UserProfile Store Constructor
     **/
    public UserProfileStore() {
        userProfileList = new ArrayList<>();
    }


    /**
     * UserProfile Populator. Populates the UserProfile List with pre-set objects.
     **/
    public void populateDefault() {
        userProfileList.add(new UserProfile("Visitor"));
        userProfileList.add(new UserProfile("Administrator"));
        userProfileList.add(new UserProfile("Director"));
        userProfileList.add(new UserProfile("User"));
    }

    /**
     * Getters and Setters Methods.
     **/
    private List<UserProfile> getOriginalUserProfileList() {
        return this.userProfileList;
    }

    public List<UserProfile> getUserProfileList() {
        return new ArrayList<>(userProfileList);
    }
    //Get userProfile by name

    public UserProfile getUserProfile(String profileName) {
        UserProfile profile = null;

        for (UserProfile i : userProfileList) {
            if (i.getUserProfileName().equalsIgnoreCase(profileName)) {
                profile = i;
            }
        }
        return profile;
    }
    //Get userProfile by ID

    public UserProfile getUserProfile(int id_UserProfile) {
        UserProfile profile = null;

        for (UserProfile i : userProfileList) {
            if (i.getId_UserProfile() == id_UserProfile) {
                profile = i;
            }
        }
        return profile;
    }


    /**
     * Create Method
     **/
    public UserProfile createProfile(String name) {
        return new UserProfile(name);
    }


    /**
     * ID_UserProfile Generator.
     */
    public int id_UserProfileGenerator() {
        int id = 1;
        if (this.userProfileList.size() > 0) {
            id = userProfileList.get(userProfileList.size() - 1).getId_UserProfile() + 1;
        }
        return id;
    } //if the object isn´t saved on the list, the id will be the same for all
    //objects. This issue will be solved when calling the save method.


    /**
     * Save UserProfile Method. Save a new UserProfile object to the UserProfile List
     **/
    public boolean saveUserProfile(UserProfile profile) {
        if (!validateProfile(profile)) {
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        } else {
            profile.setId_UserProfile(id_UserProfileGenerator());
        }
        return addUserProfile(profile);
    }

    /**
     * Add and Remove UserProfile Methods. Adds or remove a UserProfile object to the UserProfile List.
     **/

    private boolean addUserProfile(UserProfile profile) {
        if (validateId_UserProfile(profile)) {
            userProfileList.add(profile);
        } else {
            profile.setId_UserProfile(id_UserProfileGenerator());
            userProfileList.add(profile);
        }
        return true;
    }

    public boolean removeUserProfile(UserProfile profile) {
        boolean msg = false;
        if (userProfileList.contains(profile)) {
            getOriginalUserProfileList().remove(profile);
            msg = true;
        }
        return msg;
    }


    /**
     * Validation Methods.
     **/
    private boolean validateId_UserProfile(UserProfile profile) {
        boolean msg = true;

        for (UserProfile i : userProfileList) {
            if (i.getId_UserProfile() == profile.getId_UserProfile()) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    private boolean validateProfile(UserProfile profile) {
        //Check if profile already exist
        boolean msg = true;
        for (UserProfile up : userProfileList) {
            if (up.equals(profile)) {
                msg = false;
                break;
            }
        }
        return msg;
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        UserProfileStore that = (UserProfileStore) obj;
        return (this.userProfileList.equals(that.userProfileList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileList);
    }
}
