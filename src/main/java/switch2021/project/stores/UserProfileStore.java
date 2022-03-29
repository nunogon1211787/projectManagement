package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.UserProfile.UserProfile;
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
            if (i.getUserProfileName().getText().equalsIgnoreCase(profileName)) {
                profile = i;
                break;
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
     * Save UserProfile Method (Save a new UserProfile object to the UserProfile List)
     **/
    public boolean saveUserProfile(UserProfile profile) {
        if (!validateProfile(profile)) {
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        }
        return userProfileList.add(profile);
    }

    /**
     * Validation Methods
     **/

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
