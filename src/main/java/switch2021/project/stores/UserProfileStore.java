package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.UserProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class UserProfileStore {

    /**
     * Attributes
     **/
    private final List<UserProfile> userProfileList;

    /**
     * UserProfile Store Constructor
     **/
    public UserProfileStore() {
        userProfileList = new ArrayList<>();
    }

    /**
     * UserProfile Populator, that populates the UserProfile List with pre-set objects.
     **/
    public void populateDefault() {
        saveUserProfile(createProfile("Visitor"));
        saveUserProfile(createProfile("Administrator"));
        saveUserProfile(createProfile("Director"));
        saveUserProfile(createProfile("User"));
    }

    /**
     * Get UserProfile By Name Method
     **/
    public UserProfile getUserProfile(String profileName) {
        UserProfile profile = null;

        for (UserProfile i : userProfileList) {
            if (i.getUserProfileName().getDescriptionF().equalsIgnoreCase(profileName)) {
                profile = i;
                break;
            }
        }
        return profile;
    }

    /**
     * Get UserProfile By ID Method
     **/
    public UserProfile getUserProfile(int idUserProfile) {
        UserProfile profile = null;

        for (UserProfile i : userProfileList) {
            if (i.getIdProfile() == idUserProfile) {
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
     * ID_UserProfile Generator (if the object isn´t saved on the list, the id will be the same for all objects.
     * This issue will be solved when calling the save method.)
     */
    public int idUserProfileGenerator() {
        int id = 1;
        if (!this.userProfileList.isEmpty()) {
            id = userProfileList.get(userProfileList.size() - 1).getIdProfile() + 1;
        }
        return id;
    }


    /**
     * Save UserProfile Method (Save a new UserProfile object to the UserProfile List)
     **/
    public boolean saveUserProfile(UserProfile profile) {
        if (!validateProfile(profile)) {
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        }
        return addUserProfile(profile);
    }

    /**
     * Add and Remove UserProfile Methods (Adds or remove a UserProfile object to the UserProfile List)
     **/
    private boolean addUserProfile(UserProfile profile) {
        if (validateIdUserProfile(profile)) {
            userProfileList.add(profile);
        } else {
            profile.setIdProfile(idUserProfileGenerator());
            userProfileList.add(profile);
        }
        return true;
    }

    /**
     * Validation Methods
     **/
    private boolean validateIdUserProfile(UserProfile profile) {
        boolean msg = true;

        for (UserProfile i : userProfileList) {
            if (i.getIdProfile() == profile.getIdProfile()) {
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
     * Override
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
