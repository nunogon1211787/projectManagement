package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.UserProfileMapper;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserProfile.UserProfileReeng;


@Service
public class CreateUserProfileService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserProfileRepo userProfileRepositoryInterface;
    @Autowired
    private UserProfileFactoryInterface userProfileFactoryInterface;
    @Autowired
    private UserProfileMapper userProfileMapper;



    /**
     * Constructor
     **/

    public CreateUserProfileService() {
    }


    /**
     * Create and save a User Profile
     *
     * @param dto
     */
    public UserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) {

        UserProfileReeng newUserProfile = userProfileFactoryInterface.createUserProfile(dto);

        userProfileRepositoryInterface.saveUserProfile(newUserProfile);

        return userProfileMapper.toDto(newUserProfile);
    }

}

