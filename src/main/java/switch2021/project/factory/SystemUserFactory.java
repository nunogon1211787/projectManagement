package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factoryInterface.ISystemUserFactory;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

@Service
public class SystemUserFactory implements ISystemUserFactory {

    @Override
    public SystemUser createSystemUser(NewUserInfoDTO infoDTO, UserProfileId visitorId) {

        SystemUser newUser = new SystemUser(new SystemUserID(new Email(infoDTO.email)));

        newUser.setUserName(new Name(infoDTO.userName));
        newUser.setFunction(new Function(infoDTO.function));
        newUser.assignValidatedPassword(new Password(infoDTO.password), new Password(infoDTO.passwordConfirmation));
        newUser.setPhoto(new Photo(infoDTO.photo));
        newUser.assignProfileIdToUser(visitorId);

        return newUser;
    }
}