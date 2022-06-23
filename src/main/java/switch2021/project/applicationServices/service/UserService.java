package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.entities.factories.factoryInterfaces.IUserFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.dtoModel.mapper.UserMapper;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /**
     * Attributes
     */
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserFactory userFactory;
    @Autowired
    private IUserIDFactory userIDFactory;
    @Autowired
    private IUserProfileRepo profileRepo;
    @Autowired
    private IUserProfileIDFactory profileIDFactory;


    /**
     * Register User (US001)
     */
    public OutputUserDTO createAndSaveUser(NewUserInfoDTO infoDTO) throws Exception {
        OutputUserDTO outputDTO;
        User newUser = userFactory.createUser(infoDTO);

        if (!userRepo.existsById(newUser.getUserId())) {
            outputDTO = userMapper.toDto(userRepo.save(newUser));
        } else {
            throw new Exception("System User Already exists!");
        }
        return outputDTO;
    }


    /**
     * Find All Users
     */
//    public CollectionModel<OutputUserDTO> findAllUsers() {
//        List<User> usersList = userRepo.findAll();
//        return userMapper.toCollectionDTO(usersList);
//    }

    public CollectionModel<PartialUserDTO> findAllUsers() {
        List<User> usersList = userRepo.findAll();
        return userMapper.toCollectionDTO2(usersList);
    }


    /**
     * Find User, by ID
     */
    public OutputUserDTO findUserById(String id) {
        UserID userID = createUserIdByStringInputFromController(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }
        return userMapper.toDto(user);
    }


    /**
     * Search User By Parameters
     */
    public CollectionModel<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {
        List<User> allFounded = new ArrayList<>();
        List<User> usersFounded = new ArrayList<>();

        if (!inDto.name.isEmpty() || !inDto.name.isBlank())
            allFounded.addAll(userRepo.findAllByNameContains(inDto.name));

        if (!inDto.function.isEmpty() || !inDto.function.isBlank())
            allFounded.addAll(userRepo.findAllByFunctionContains(inDto.function));

        if (!inDto.profile.isEmpty() || !inDto.profile.isBlank()) {
            UserProfileID profileId = profileIDFactory.createUserProfileID(inDto.profile);
            if (profileRepo.existsByUserProfileId(profileId)) {
                allFounded.addAll(userRepo.findAllByUserProfileContains(profileId));
            } else {
                throw new IllegalArgumentException("This user profile does not exist!");
            }
        }

        allFounded.forEach(user -> {
            if (!usersFounded.contains(user)) {
                usersFounded.add(user);
            }
        });
        return userMapper.toCollectionDTO(usersFounded);
    }


    /**
     * Update Personal Data and Change Password (US010 and US011)
     */
    public OutputUserDTO updatePersonalData(String id, UpdateDataDTO updateDataDTO) {
        UserID userID = createUserIdByStringInputFromController(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        if (updateDataDTO.newPassword != null && updateDataDTO.oldPassword != null) {
            user.updatePassword(updateDataDTO.oldPassword, updateDataDTO.newPassword);
        } else {
            user.editPersonalData(updateDataDTO.userName, updateDataDTO.function, updateDataDTO.photo);
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }


    /**
     * Update assigned profiles (US006)
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public OutputUserDTO assignUserProfile(String id, UpdateUserProfileDTO profileDTO) {
        UserID userID = createUserIdByStringInputFromController(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        UserProfileID profileID = profileIDFactory.createUserProfileID(profileDTO.profileId);
        //Validate if exist the profile
        if (!profileRepo.existsByUserProfileId(profileID)) {
            throw new IllegalArgumentException("This user profile does not exist!");
        }
        //Validate if the user has the user profile assigned
        if (!user.hasProfile(profileID)) {
            user.toAssignProfile(profileID);
        } else {
            throw new IllegalArgumentException("This user profile was already assigned!");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    public OutputUserDTO removeUserProfile(String id, UpdateUserProfileDTO profileDTO) {
        UserID userID = createUserIdByStringInputFromController(id);
        UserProfileID profileID;
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        //Validate if the profile is Visitor, all Users must have the visitor Profile.
        if (!profileDTO.profileId.equalsIgnoreCase("visitor")) {
            profileID = profileIDFactory.createUserProfileID(profileDTO.profileId);
        } else {
            throw new IllegalArgumentException("The user profile 'Visitor' can not be removed!");
        }
        //Validate if exist the profile
        if (!profileRepo.existsByUserProfileId(profileID)) {
            throw new IllegalArgumentException("This user profile does not exist!");
        }
        //Validate if the user has the user profile assigned
        if (user.hasProfile(profileID)) {
            user.removeProfile(profileID);
        } else {
            throw new IllegalArgumentException("This user profile was not assigned!");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }


    /**
     * Active and Inactive User (US002, US025 and US026)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public OutputUserDTO activateUser(String id) {
        UserID userID = createUserIdByStringInputFromController(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        if (!user.activateStatus()) {
            throw new IllegalArgumentException("This user is already activated");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    public OutputUserDTO inactivateUser(String id) {
        UserID userID = createUserIdByStringInputFromController(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        if (!user.inactivateStatus()) {
            throw new IllegalArgumentException("This user is already inactivated");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }


    /**
     * Create a Request to assign a user profile to a user (US003)
     */
    public boolean createAndAddRequest(String id, RequestDTO requestDTO) {
        UserID userID = createUserIdByStringInputFromController(id);
        UserProfileID profileID = profileIDFactory.createUserProfileID(requestDTO.getProfileId());
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }

        if (profileRepo.existsByUserProfileId(profileID)) {
            user.createProfileRequest(profileID);
        } else {
            throw new IllegalArgumentException("This profile does not exist!");
        }
        userRepo.save(user);
        return true;
    }


    /**
     * Delete User
     */
    public void deleteUser(String id) throws NullPointerException {
        UserID userID = userIDFactory.createUserID(id);
        userRepo.delete(userID);
    }

    private UserID createUserIdByStringInputFromController(String id) {
        return userIDFactory.createUserID(id);
    }
}
