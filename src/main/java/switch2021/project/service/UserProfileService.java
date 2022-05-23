package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.factoryInterface.IUserProfileFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.UserProfileMapper;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserProfileService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserProfileRepo userProfileRepositoryInterface;
    @Autowired
    private IUserProfileFactory iUserProfileFactory;
    @Autowired
    private IUserProfileIDFactory factoryId;
    @Autowired
    private UserProfileMapper userProfileMapper;


    /**
     * Create and save a User Profile
     */
    public UserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) {

        UserProfile newUserProfile = iUserProfileFactory.createUserProfile(dto);
        if(userProfileRepositoryInterface.save(newUserProfile).isEmpty()){
            throw new IllegalArgumentException("User Profile Already exists!");
        }
        return userProfileMapper.toDto(newUserProfile);
    }

    /**
     * To get all profiles
     */
    public List<UserProfileDTO> showAllProfiles() {

        List<UserProfile> profiles = userProfileRepositoryInterface.findAll();
        List<UserProfileDTO> profilesDto = new ArrayList<>();

        profiles.forEach(profile -> profilesDto.add(userProfileMapper.toDto(profile)));

        return profilesDto;
    }

    /**
     * Find a requested user profile
     */

    public UserProfileDTO showUserProfileRequested(String id) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = userProfileRepositoryInterface.findByUserProfileID(profileId);

        if(requested.isEmpty()){
            throw new Exception("Profile does not exist.");
        }

        return userProfileMapper.toDto(requested.get());
    }

    /**
     * To edit a profile
     */

    public UserProfileDTO editARequestedUserProfile(String id, UserProfileDTO inDto) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = userProfileRepositoryInterface.findByUserProfileID(profileId);

        if(requested.isEmpty()){
            throw new Exception("Profile does not exist.");
        }

        userProfileRepositoryInterface.deleteById(profileId);

        return createAndSaveUserProfile(inDto);
    }

    /**
     * To delete a profile
     */
    public void deleteARequestedUserProfile(String id) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        if(!userProfileRepositoryInterface.deleteById(profileId)){
            throw new Exception("User profile does not exist.");
        }
    }
}

