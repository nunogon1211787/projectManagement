package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.factory.UserProfileFactory;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.List;

//@Component
public interface IUserProfileRepo {

    /**
     * Finds all user profile
     *
     * @return userProfileList if found, else {@code null}
     */
    //List<UserProfile> findUserProfile();

    /**
     * Save user story
     *
     * @param profile
     * @return boolean result
     */

    boolean saveUserProfile(UserProfile profile);

}
