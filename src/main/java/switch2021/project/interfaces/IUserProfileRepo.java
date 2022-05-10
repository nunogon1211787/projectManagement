package switch2021.project.interfaces;

import switch2021.project.model.UserProfile.UserProfileReeng;

import java.util.List;


public interface IUserProfileRepo {

    /**
     * The repository should be able to find a object using given Identity.
     */
    UserProfileReeng findUserProfileByDescription(String userProfileName);

    /**
     * Finds all user profile
     *
     * @return userProfileList if found, else {@code null}
     */
    List<UserProfileReeng> findAllUserProfiles();

    /**
     * Save user story
     *
     * @param profile
     * @return boolean result
     */
    boolean saveUserProfile(UserProfileReeng profile);

    boolean existsByDescription(String userProfileName);
}
