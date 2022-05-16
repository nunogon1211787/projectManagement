package switch2021.project.interfaces;

import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.List;


public interface IUserProfileRepo {

    /**
     * The repository should be able to find a object using given Identity.
     */
    UserProfile findUserProfileByDescription(String userProfileName);

    /**
     * Finds all user profile
     *
     * @return userProfileList if found, else {@code null}
     */
    List<UserProfile> findAllUserProfiles();

    /**
     * Save user story
     */
    boolean saveUserProfile(UserProfile profile);

    boolean existsByDescription(String userProfileName);

    boolean existsByUserProfileId(UserProfileID profile);
}
