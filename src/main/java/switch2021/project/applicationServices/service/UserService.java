package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.dtoModel.dto.OutputUserDTO;
import switch2021.project.dtoModel.dto.SearchUserDTO;
import switch2021.project.dtoModel.dto.UpdateDataDTO;
import switch2021.project.dtoModel.dto.old.RequestDTO;
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
        User newUser = userFactory.createUser(infoDTO);

        Optional<User> savedUser = userRepo.save(newUser);
        OutputUserDTO outputDTO;

        if (savedUser.isPresent()) {
            outputDTO = userMapper.toDto(savedUser.get());
        } else {
            throw new Exception("System User Already exists!");
        }
        return outputDTO;
    }


    /**
     * Find All Users
     */
    public CollectionModel<OutputUserDTO> findAllUsers() {

        List<User> usersList = userRepo.findAll();

        return userMapper.toCollectionDTO(usersList);
    }


    /**
     * Find User, by ID
     */
    public OutputUserDTO findUserById(String id) throws Exception {
        UserID userID = userIDFactory.createUserID(id);
        Optional<User> opUser = userRepo.findByUserId(userID);

        if (opUser.isEmpty()) {
            throw new Exception("User does not exists!");
        }
        return userMapper.toDto(opUser.get());
    }


    /**
     * Search User By Parameters
     */
    public List<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {

        List<User> allFounded = new ArrayList<>();

        List<User> usersFounded = new ArrayList<>();

        List<OutputUserDTO> usersFoundedDto = new ArrayList<>();

        if (!inDto.name.isEmpty() || !inDto.name.isBlank())
            allFounded.addAll(userRepo.findAllByNameContains(inDto.name));

        if (!inDto.function.isEmpty() || !inDto.function.isBlank())
            allFounded.addAll(userRepo.findAllByFunctionContains(inDto.function));

        if (!inDto.profile.isEmpty() || !inDto.profile.isBlank()) {
            UserProfileID profile = profileIDFactory.createUserProfileID(inDto.profile);

            if (profileRepo.existsByUserProfileId(profile)) {

                allFounded.addAll(userRepo.findAllByUserProfileId(profile));

            }
        }

        allFounded.forEach(user -> {
            if (!usersFounded.contains(user)) {
                usersFounded.add(user);
            }
        });

        usersFounded.forEach(user -> usersFoundedDto.add(userMapper.toDto(user)));

        return usersFoundedDto;
    }


    /**
     * Update Personal Data and Change Password
     */
    public OutputUserDTO updatePersonalData(String id, UpdateDataDTO updateDataDTO) {
        UserID userID = userIDFactory.createUserID(id);
        Optional<User> opUser = userRepo.findByUserId(userID);
        User user;

        if (opUser.isPresent()) {
            user = opUser.get();

            if (updateDataDTO.newPassword != null && updateDataDTO.oldPassword != null) {
                user.updatePassword(updateDataDTO.oldPassword,updateDataDTO.newPassword);
            } else {
                user.editPersonalData(updateDataDTO.userName, updateDataDTO.function, updateDataDTO.photo);
            }
            Optional<User> updatedUser = userRepo.update(user);
            return userMapper.toDto(updatedUser.get());
        } else
            return null;
    }

    /**
     * Active and Inactive User (US002, US025 and US026)
     */
    public OutputUserDTO activateUser(String id) {
        UserID userID = userIDFactory.createUserID(id);
        Optional<User> opUser = userRepo.findByUserId(userID);
        User user;

        if (opUser.isPresent()) {
            user = opUser.get();

            if (!user.activateStatus()) {
                throw new IllegalArgumentException("This user is already activated");
            }
        } else {
            return null;
        }
        userRepo.update(user);
        return userMapper.toDto(user);
    }

    public OutputUserDTO inactivateUser(String id) {
        UserID userID = userIDFactory.createUserID(id);
        Optional<User> opUser = userRepo.findByUserId(userID);
        User user;

        if (opUser.isPresent()) {
            user = opUser.get();

            if (!user.inactivateStatus()) {
                throw new IllegalArgumentException("This user is already inactivated");
            }
        } else {
            return null;
        }
        userRepo.update(user);
        return userMapper.toDto(user);
    }


    public boolean createAndAddRequest(String id, RequestDTO requestDTO) {
        UserID userID = userIDFactory.createUserID(id);
        UserProfileID profileID = profileIDFactory.createUserProfileID(requestDTO.getProfileId());

        Optional<User> opUser = userRepo.findByUserId(userID);
        User user;

        if (opUser.isPresent()) {
            user = opUser.get();
            if (profileRepo.existsByUserProfileId(profileID)) {
                user.createProfileRequest(profileID);
            } else {
                throw new IllegalArgumentException("This profile does not exist!");
            }
        } else {
            return false;
        }
        userRepo.update(user);
        return true;
    }

    /**
     * Delete User
     */
    public void deleteUser(String id) throws Exception {
        UserID userID = userIDFactory.createUserID(id);
        if (!userRepo.delete(userID)) {
            throw new IllegalArgumentException("User does not exists!");
        }
    }
}
