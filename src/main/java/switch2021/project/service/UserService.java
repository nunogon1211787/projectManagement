package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.*;
import switch2021.project.factoryInterface.IUserFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserFactory userFactory;
    @Autowired
    private IUserProfileRepo profileRepo;
    @Autowired
    private IUserProfileIDFactory profileIDFactory;

    public OutputUserDTO createAndSaveUser(NewUserInfoDTO infoDTO) {
        User newUser = userFactory.createUser(infoDTO);
        if (!userRepo.save(newUser))
            throw new IllegalArgumentException("email already exists");
        return userMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {
        User found = this.userRepo.findByUserID(email);
        return this.userMapper.toDto(found);
    }

    public OutputUserDTO createAndSaveUserJPA (NewUserInfoDTO infoDTO) throws Exception{
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

    public OutputUserDTO updatePersonalData(IdDTO idDTO, UpdateDataDTO updateDataDTO) {

        User user = userRepo.findByUserID(idDTO.id);

        if (updateDataDTO.newPassword == null && updateDataDTO.oldPassword == null) {
            user.editPersonalData(updateDataDTO.userName, updateDataDTO.function, updateDataDTO.photo);
        }
        if (updateDataDTO.userName == null && updateDataDTO.function == null) {
            user.updatePassword(updateDataDTO.oldPassword, updateDataDTO.newPassword);
        }
        return userMapper.toDto(user);
    }

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
}
