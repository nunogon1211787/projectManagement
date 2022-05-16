package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.IUserProfileFactory;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.UserProfileMapper;
import switch2021.project.model.UserProfile.UserProfile;


@Service
public class CreateUserProfileService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserProfileRepo userProfileRepositoryInterface;
    @Autowired
    private IUserProfileFactory iUserProfileFactory;
    @Autowired
    private UserProfileMapper userProfileMapper;


    /**
     * Create and save a User Profile
     */
    public UserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) {

        UserProfile newUserProfile = iUserProfileFactory.createUserProfile(dto);
        if(!userProfileRepositoryInterface.saveUserProfile(newUserProfile)){
            throw new IllegalArgumentException("User Profile Already exists!");
        }
        return userProfileMapper.toDto(newUserProfile);
    }
}

