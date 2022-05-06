package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

@Component
public class SystemUserFactory implements ISystemUserFactory {

    @Autowired
    private ISystemUserIDFactory idFactory;
    @Autowired
    private INameFactory nameFactory;
    @Autowired
    private IFunctionFactory functionFactory;
    @Autowired
    private IPasswordFactory passwordFactory;
    @Autowired
    private IPhotoFactory photoFactory;
    @Autowired
    private IUserProfileIDFactory userProfileIDFactory;

    public SystemUserFactory(ISystemUserIDFactory idFactory, INameFactory nameFactory,
                             IFunctionFactory functionFactory, IPasswordFactory passwordFactory,
                             IPhotoFactory photoFactory, IUserProfileIDFactory userProfileIDFactory) {
        this.idFactory = idFactory;
        this.nameFactory = nameFactory;
        this.functionFactory = functionFactory;
        this.passwordFactory = passwordFactory;
        this.photoFactory = photoFactory;
        this.userProfileIDFactory = userProfileIDFactory;
    }

    @Override
    public SystemUser createSystemUser(NewUserInfoDTO infoDTO) {
        SystemUserID systemUserID = idFactory.createSystemUserID(infoDTO.email);
        Name userName = nameFactory.createName(infoDTO.userName);
        Photo photo = photoFactory.createPhoto(infoDTO.photo);
        Password password = passwordFactory.createPassword(infoDTO.password);
        Password passwordConfirmation = passwordFactory.createPassword(infoDTO.passwordConfirmation);
        Function function = functionFactory.createFunction(infoDTO.function);
        UserProfileId visitorID = userProfileIDFactory.createUserProfileID("Visitor");

        return new SystemUser(systemUserID, userName, photo, password, passwordConfirmation, function, visitorID);
    }
}