package switch2021.project.model.SystemUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.RegisterUserDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.SystemUserFactoryInterface;
import switch2021.project.interfaces.SystemUserRepositoryInterface;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.valueObject.UserProfileId;
import switch2021.project.repositories.UserProfileRepository;

@Service
public class SystemUserService {

    private SystemUserRepositoryInterface systemUserRepositoryInterface;
    private UserProfileRepository userProfileRepository; //terá uma interface...
    private SystemUserMapper systemUserMapper;
    private SystemUserFactoryInterface systemUserFactoryInterface;

    @Autowired
    public SystemUserService(SystemUserRepositoryInterface systemUserRepositoryInterface,
                             UserProfileRepository userProfileRepository, SystemUserMapper systemUserMapper,
                             SystemUserFactoryInterface systemUserFactoryInterface) {
        this.systemUserRepositoryInterface = systemUserRepositoryInterface;
        this.userProfileRepository = userProfileRepository;
        this.systemUserMapper = systemUserMapper;
        this.systemUserFactoryInterface = systemUserFactoryInterface;
    }

    public OutputUserDTO createAndSaveSystemUser(RegisterUserDTO dto) {
        UserProfileId visitorId = userProfileRepository.getUserProfile("Visitor").getUserProfileId();

        SystemUser newUser = systemUserFactoryInterface.createSystemUser(dto.userName, dto.email,
                dto.function, dto.password, dto.passwordConfirmation, dto.photo, visitorId);

        systemUserRepositoryInterface.saveSystemUser(newUser);

        return systemUserMapper.toDto(newUser);
    }

    public OutputUserDTO findSystemUserByEmail(String email) {//showDTO ou OutputDTO
        SystemUser found = this.systemUserRepositoryInterface.findSystemUserByEmail(email);

        return this.systemUserMapper.toDto(found);
    }


}
