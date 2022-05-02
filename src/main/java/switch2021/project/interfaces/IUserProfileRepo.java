package switch2021.project.interfaces;

import switch2021.project.model.UserProfile.UserProfile;

import java.util.List;

//@Component
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
     *
     * @param profile
     * @return boolean result
     */
    boolean saveUserProfile(UserProfile profile);

    boolean existsByDescription(String userProfileName);
}
