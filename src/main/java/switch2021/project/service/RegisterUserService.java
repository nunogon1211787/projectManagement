package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.ISystemUserFactory;
import switch2021.project.factoryInterface.UserProfileFactoryInterface;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileId;

@Service
public class RegisterUserService {

    @Autowired
    private ISystemUserRepo systemUserRepo;
    @Autowired
    private UserProfileFactoryInterface userProfileFactory;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private ISystemUserFactory systemUserFactory;

    public RegisterUserService(ISystemUserRepo systemUserRepo, UserProfileFactoryInterface userProfileFactory,
                               SystemUserMapper systemUserMapper, ISystemUserFactory systemUserFactory) {
        this.systemUserRepo = systemUserRepo;
        this.userProfileFactory = userProfileFactory;
        this.systemUserMapper = systemUserMapper;
        this.systemUserFactory = systemUserFactory;
    }

    public OutputUserDTO createAndSaveSystemUser(NewUserInfoDTO infoDTO) {
        UserProfileId visitorID = userProfileFactory.createUserProfile("Visitor").getUserProfileId();
        SystemUser newUser = systemUserFactory.createSystemUser(infoDTO, visitorID);

        systemUserRepo.saveSystemUser(newUser);

        return systemUserMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {
        SystemUser found = this.systemUserRepo.findSystemUserByEmail(email);

        return this.systemUserMapper.toDto(found);
    }
}
