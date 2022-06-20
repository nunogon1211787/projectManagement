package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.dtoModel.mapper.UserProfileMapper;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    @Autowired
    private IUserProfileWebRepository iUserProfileWebRepository;

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
    public CollectionModel<UserProfileDTO> getAllProfiles() {

        List<UserProfile> userProfileList = userProfileRepositoryInterface.findAll();
        List<UserProfile> userProfileWebList = iUserProfileWebRepository.findAll();

        CollectionModel<UserProfileDTO> outputUserProfileList_DTO = userProfileMapper.toCollectionModel(userProfileWebList);
        CollectionModel<UserProfileDTO> outputUserProfileDTO_List = userProfileMapper.toCollectionModel(userProfileList);

        List<UserProfileDTO> newList = Stream.concat(outputUserProfileList_DTO.getContent().stream(), outputUserProfileDTO_List.getContent().stream())
                .collect(Collectors.toList());

        return CollectionModel.of(newList);

    }

    /**
     * Find a requested user profile
     */

    public UserProfileDTO showUserProfileRequested(String id) {
        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = userProfileRepositoryInterface.findByUserProfileID(profileId);

        return requested.map(userProfile -> userProfileMapper.toDto(userProfile))
                .orElse(null);
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

