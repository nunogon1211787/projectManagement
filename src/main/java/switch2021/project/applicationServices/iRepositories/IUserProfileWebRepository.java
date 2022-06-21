package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import java.util.List;

public interface IUserProfileWebRepository {

    /**
     * Find All User Profiles
     * @return User Profile List
     */
    List<UserProfile> findAll();
}
