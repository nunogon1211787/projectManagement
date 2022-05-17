package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.IUserFactory;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;

@Service
public class RegisterUserService {

    @Autowired
    private IUserRepo systemUserRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserFactory systemUserFactory;

    public OutputUserDTO createAndSaveUser(NewUserInfoDTO infoDTO) {
        User newUser = systemUserFactory.createUser(infoDTO);
        if (!systemUserRepo.save(newUser))
            throw new IllegalArgumentException("email already exists");
        return userMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {
        User found = this.systemUserRepo.findByUserID(email);
        return this.userMapper.toDto(found);
    }
}
