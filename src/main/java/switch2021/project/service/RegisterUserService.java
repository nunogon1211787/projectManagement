package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.ISystemUserFactory;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.SystemUser.SystemUser;

@Service
public class RegisterUserService {

    @Autowired
    private ISystemUserRepo systemUserRepo;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private ISystemUserFactory systemUserFactory;

    public OutputUserDTO createAndSaveSystemUser(NewUserInfoDTO infoDTO) {
        SystemUser newUser = systemUserFactory.createSystemUser(infoDTO);
        if (!systemUserRepo.saveSystemUser(newUser))
            throw new IllegalArgumentException("email already exists");
        return systemUserMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {
        SystemUser found = this.systemUserRepo.findSystemUserByEmail(email);
        return this.systemUserMapper.toDto(found);
    }
}
