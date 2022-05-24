package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dto.*;
import switch2021.project.factoryInterface.IUserFactory;
import switch2021.project.factoryInterface.IUserIDFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserProfileID;

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
     * Register User
     */

    public OutputUserDTO createAndSaveUser (NewUserInfoDTO infoDTO) throws Exception{

        User user = userFactory.createUser(infoDTO);

        Optional<User> userSaved = userRepo.saveReeng(user);

        OutputUserDTO outputUserDTO;

        if (userSaved.isPresent()) {
            outputUserDTO = userMapper.toDto(userSaved.get());
        } else {
            throw new Exception("System User Already exists!");
        }

        return outputUserDTO;
    }

    /**
     * Find All Users
     */

    public CollectionModel<OutputUserDTO> findAllUsers() {

        List<User> usersList = userRepo.findAllSystemUsers();

        return userMapper.toCollectionDTO(usersList);
    }


    /**
     * Find User, by ID
     */

    public OutputUserDTO findUserById(String idDTO) throws Exception{

        SystemUserID userID = userIDFactory.createUserID(idDTO);

        Optional<User> opUser = userRepo.findUserById(userID);

        if (opUser.isEmpty()) {
            throw new Exception("User does not exists!");
        }
        return userMapper.toDto(opUser.get());
    }


    /**
     * Update Personal Data and Change Password
     */

    public OutputUserDTO updatePersonalData(IdDTO idDTO, UpdateDataDTO updateDataDTO) {
        SystemUserID userID = userIDFactory.createUserID(idDTO.id);
        Optional<User> opUser = userRepo.findUserById(userID);
        if (opUser.isPresent()) {
            User user = opUser.get();

            if (updateDataDTO.newPassword == null && updateDataDTO.oldPassword == null) {
                user.editPersonalData(updateDataDTO.userName, updateDataDTO.function, updateDataDTO.photo);
            }
            if (updateDataDTO.userName == null && updateDataDTO.function == null) {
                user.updatePassword(updateDataDTO.oldPassword, updateDataDTO.newPassword);
            }
            return userMapper.toDto(user);
        } else
            return null;
    }

    /**
     * Search User By Parameters
     */

    public List<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {

        List<User> allFounded = new ArrayList<>();

        List<User> usersFounded = new ArrayList<>();

        List<OutputUserDTO> usersFoundedDto = new ArrayList<>();

        if(!inDto.id.isEmpty() || !inDto.id.isBlank())
            allFounded.addAll(userRepo.findAllBySystemUserIdContains(inDto.id));

        if(!inDto.name.isEmpty() || !inDto.name.isBlank())
            allFounded.addAll(userRepo.findAllByNameContains(inDto.name));

        if(!inDto.function.isEmpty() || !inDto.function.isBlank())
            allFounded.addAll(userRepo.findAllByFunctionContains(inDto.function));

        if (!inDto.profile.isEmpty() || !inDto.profile.isBlank()) {
            UserProfileID profile = profileIDFactory.createUserProfileID(inDto.profile);

            if (profileRepo.existsByUserProfileId(profile)) {

                allFounded.addAll(userRepo.findAllByUserProfileId(profile));

            }
        }

        allFounded.forEach(user -> {
            if(!usersFounded.contains(user)){
                usersFounded.add(user);
            }
        });

        usersFounded.forEach(user -> usersFoundedDto.add(userMapper.toDto(user)));

        return usersFoundedDto;
    }

    /**
     * Delete User
     */

    public void deleteUser (String id) throws Exception {

        SystemUserID systemUserID = userIDFactory.createUserID(id);

        if(!userRepo.deleteUser(systemUserID)) {
            throw new IllegalArgumentException("User does not exists!");
        }

    }

}
