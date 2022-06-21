package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dtoModel.dto.OutputUserProfileDTO;
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
    private IUserProfileRepo userProfileRepo;
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
    public OutputUserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) throws Exception {

        UserProfile newUserProfile = iUserProfileFactory.createUserProfile(dto);
        Optional<UserProfile> userProfileSaved= userProfileRepo.save(newUserProfile);

        OutputUserProfileDTO outputUserProfileDTO;

        if(userProfileSaved.isPresent()){
            outputUserProfileDTO= userProfileMapper.toDTO(userProfileSaved.get());
        }
        else {
            throw new Exception ("This User Profile already exists!");
        }
        return outputUserProfileDTO;
    }

    /**
     * To get all profiles
     */
    public CollectionModel<UserProfileDTO> getAllProfiles() {

        List<UserProfile> userProfileList = userProfileRepositoryInterface.findAll();
        List<UserProfile> userProfileWebList = iUserProfileWebRepository.findAll();
    public CollectionModel<OutputUserProfileDTO> showAllProfiles() {

        CollectionModel<UserProfileDTO> outputUserProfileList_DTO = userProfileMapper.toCollectionModel(userProfileWebList);
        CollectionModel<UserProfileDTO> outputUserProfileDTO_List = userProfileMapper.toCollectionModel(userProfileList);

        List<UserProfileDTO> newList = Stream.concat(outputUserProfileList_DTO.getContent().stream(), outputUserProfileDTO_List.getContent().stream())
                .collect(Collectors.toList());

        return CollectionModel.of(newList);
        return userProfileMapper.toCollectionDTO(userProfileRepo.findAll());

    }

    /**
     * Find a requested user profile
     */

    public OutputUserProfileDTO findUserProfileRequested(String id) throws Exception {
        UserProfileID profileID = factoryId.createUserProfileID(id);
       Optional<UserProfile> userProfile = userProfileRepo.findByUserProfileID(profileID);

        if(userProfile.isEmpty()){
            throw new Exception("We can not find the User Profile requested :(");
        }

        return userProfileMapper.toDTO(userProfile.get());

    }

    /**
     * To edit a profile
     */
    public OutputUserProfileDTO editARequestedUserProfile(String id, UserProfileDTO inDto) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = userProfileRepo.findByUserProfileID(profileId);

        if(requested.isEmpty()){
            throw new Exception("Profile does not exist.");
        }

        userProfileRepo.deleteById(profileId);

        return createAndSaveUserProfile (inDto);
    }

    /**
     * To delete a profile
     */
    public void deleteARequestedUserProfile(String id) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        if(!userProfileRepo.deleteById(profileId)){
            throw new Exception("User profile does not exist.");
        }
    }
}

