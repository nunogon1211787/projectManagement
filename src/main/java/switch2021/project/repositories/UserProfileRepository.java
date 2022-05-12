package switch2021.project.repositories;

import org.springframework.stereotype.Repository;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserProfileRepository {

    /**
     * Attributes
     **/
    private final List<UserProfile> userProfileList;

    /**
     * UserProfile Store Constructor
     **/
    public UserProfileRepository() {
        userProfileList = new ArrayList<>();
    }


    public UserProfile findUserProfileByDescription(String profileName) {
        UserProfile profile = null;

        for (UserProfile i : userProfileList) {
            if (i.getUserProfileId().getUserProfileName().getText().equalsIgnoreCase(profileName)) {
                profile = i;
                break;
            }
        }
        return profile;
    }


    public List<UserProfile> findAllUserProfiles() {
        return new ArrayList<>(this.userProfileList);
    }

    /**
     * Save UserProfile Method (Save a new UserProfile object to the UserProfile List)
     **/

    public boolean saveUserProfile(UserProfile profile) {
        if (profile == null || existsByDescription(profile.getUserProfileId().getUserProfileName().getText())) {
            return false;
        } else {
            return this.userProfileList.add(profile);
        }
    }

    public boolean existsByDescription(String userProfileText) {
        for (UserProfile userProfile : this.userProfileList) {
            if (userProfile.getUserProfileId().getUserProfileName().getText().equalsIgnoreCase(userProfileText.trim())) {
                return true;
            }
        }
        return false;
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

 // mudar para o service
    /**
     * Create Method
     **/
    public UserProfile createProfile(String name) {
        return new UserProfile(name);
    }


    /**
     * Validation Methods
     **/
/*    private boolean validateProfile(UserProfile profile) {
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

 */


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileRepository)) return false;
        UserProfileRepository that = (UserProfileRepository) obj;
        return (this.userProfileList.equals(that.userProfileList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileList);
    }
}
