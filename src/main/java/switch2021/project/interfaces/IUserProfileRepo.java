package switch2021.project.interfaces;

import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.List;
import java.util.Optional;


public interface IUserProfileRepo {

    /**
     * The repository should be able to find a object using given Identity.
     */
    Optional<UserProfile> findByUserProfileID(UserProfileID userProfileID);

    /**
     * Finds all user profile
     *
     * @return userProfileList if found, else {@code null}
     */
    List<UserProfile> findAll();

    /**
     * Save user story
     */
    Optional<UserProfile> save(UserProfile profile);

    boolean existsByUserProfileId(UserProfileID userProfileID);

    boolean deleteById (UserProfileID userProfileID);
}
