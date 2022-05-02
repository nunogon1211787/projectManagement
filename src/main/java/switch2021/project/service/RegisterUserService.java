package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.ISystemUserFactory;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileId;

@Service
public class RegisterUserService {

    @Autowired
    private ISystemUserRepo systemUserRepo;
    @Autowired
    private IUserProfileRepo userProfileRepo;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private ISystemUserFactory systemUserFactory;

    public RegisterUserService(ISystemUserRepo systemUserRepo, IUserProfileRepo userProfileRepo,
                               SystemUserMapper systemUserMapper, ISystemUserFactory systemUserFactory) {
        this.systemUserRepo = systemUserRepo;
        this.userProfileRepo = userProfileRepo;
        this.systemUserMapper = systemUserMapper;
        this.systemUserFactory = systemUserFactory;
    }

    public OutputUserDTO createAndSaveSystemUser(NewUserInfoDTO infoDTO) {
        UserProfileId visitorId = userProfileRepo.findUserProfileByDescription("Visitor").getUserProfileId();
        if (visitorId == null)
            return null;//must be dto null

        SystemUser newUser = systemUserFactory.createSystemUser(infoDTO, visitorId);

        systemUserRepo.saveSystemUser(newUser);

        return systemUserMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {
        SystemUser found = this.systemUserRepo.findSystemUserByEmail(email);

        return this.systemUserMapper.toDto(found);
    }
}
