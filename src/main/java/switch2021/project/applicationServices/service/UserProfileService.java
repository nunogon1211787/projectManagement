package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.dtoModel.mapper.UserProfileMapper;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.Optional;

@Service
public class UserProfileService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserProfileRepo iUserProfileRepo;
    @Autowired
    private IUserProfileFactory iUserProfileFactory;
    @Autowired
    private IUserProfileIDFactory factoryId;
    @Autowired
    private UserProfileMapper userProfileMapper;


    /**
     * Create and save a User Profile (US013)
     */
    public UserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) {
        UserProfile newUserProfile = iUserProfileFactory.createUserProfile(dto);

        if(iUserProfileRepo.existsByUserProfileId(newUserProfile.getUserProfileId()) ){
            throw new IllegalArgumentException("User Profile Already exists!");
        }
        UserProfile userProfile = iUserProfileRepo.save(newUserProfile);
        return userProfileMapper.toDto(userProfile);
    }


    /**
     * To get all profiles
     */
    public CollectionModel<UserProfileDTO> showAllProfiles() {
        return userProfileMapper.toCollectionModel(iUserProfileRepo.findAll());
    }


    /**
     * Find a requested user profile
     */
    public UserProfileDTO showUserProfileRequested(String id) {
        UserProfileID profileId = factoryId.createUserProfileID(id);
        Optional<UserProfile> requested = iUserProfileRepo.findByUserProfileID(profileId);

        UserProfile userProfile = requested.flatMap(up -> requested).orElse(null);

        if (userProfile == null) {
            throw new NullPointerException("Profile does not exist!");
        }
        return userProfileMapper.toDto(userProfile);
    }


    /**
     * To edit a profile
     */
    public UserProfileDTO editARequestedUserProfile(String id, UserProfileDTO inDto) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = iUserProfileRepo.findByUserProfileID(profileId);

        if(requested.isEmpty()){
            throw new Exception("Profile does not exist.");
        }

        iUserProfileRepo.deleteById(profileId);

        return createAndSaveUserProfile(inDto);
    }


    /**
     * To delete a profile
     */
    public void deleteARequestedUserProfile(String id) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        if(!iUserProfileRepo.deleteById(profileId)){
            throw new Exception("User profile does not exist.");
        }
    }
}

