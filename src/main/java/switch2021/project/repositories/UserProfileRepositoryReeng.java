package switch2021.project.repositories;

import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.model.UserProfile.UserProfileReeng;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserProfileRepositoryReeng implements IUserProfileRepo {

    /**
     * Attributes
     **/
    private final List<UserProfileReeng> userProfileList;

    /**
     * UserProfile Store Constructor
     **/
    public UserProfileRepositoryReeng() {
        this.userProfileList = new ArrayList<>();
    }

    @Override
    public UserProfileReeng findUserProfileByDescription(String profileName) {
        UserProfileReeng profile = null;

        for (UserProfileReeng i : userProfileList) {
            if (i.getUserProfileId().getUserProfileName().getText().equalsIgnoreCase(profileName)) {
                profile = i;
                break;
            }
        }
        return profile;
    }

    @Override
    public List<UserProfileReeng> findAllUserProfiles() {
        return new ArrayList<>(this.userProfileList);
    }

    /**
     * Save UserProfile Method (Save a new UserProfile object to the UserProfile List)
     **/
    @Override
    public boolean saveUserProfile(UserProfileReeng profile) {
        if (profile == null || existsByDescription(profile.getUserProfileId().getUserProfileName().getText())) {
            return false;
        } else {
            return this.userProfileList.add(profile);
        }
    }

    @Override
    public boolean existsByDescription(String userProfileText) {
        for (UserProfileReeng userProfile : this.userProfileList) {
            if (userProfile.getUserProfileId().getUserProfileName().getText().equalsIgnoreCase(userProfileText.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileRepository)) return false;
        UserProfileRepositoryReeng that = (UserProfileRepositoryReeng) obj;
        return (this.userProfileList.equals(that.userProfileList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileList);
    }
}
