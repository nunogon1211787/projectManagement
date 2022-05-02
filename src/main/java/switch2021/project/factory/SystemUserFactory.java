package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factoryInterface.ISystemUserFactory;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

@Service
public class SystemUserFactory implements ISystemUserFactory {

    @Autowired
    private IValueObjectsFactory<SystemUserID> idFactory;
    @Autowired
    private IValueObjectsFactory<Name> nameFactory;
    @Autowired
    private IValueObjectsFactory<Function> functionFactory;
    @Autowired
    private IValueObjectsFactory<Password> passwordFactory;
    @Autowired
    private IValueObjectsFactory<Photo> photoFactory;

    public SystemUserFactory(IValueObjectsFactory<SystemUserID> idFactory, IValueObjectsFactory<Name> nameFactory,
                             IValueObjectsFactory<Function> functionFactory,
                             IValueObjectsFactory<Password> passwordFactory, IValueObjectsFactory<Photo> photoFactory) {
        this.idFactory = idFactory;
        this.nameFactory = nameFactory;
        this.functionFactory = functionFactory;
        this.passwordFactory = passwordFactory;
        this.photoFactory = photoFactory;
    }

    @Override
    public SystemUser createSystemUser(NewUserInfoDTO infoDTO, UserProfileId visitorId) {
        SystemUser newUser = new SystemUser(idFactory.create(infoDTO.email));

        newUser.assignName(nameFactory.create(infoDTO.userName));
        newUser.assignFunction(functionFactory.create(infoDTO.function));
        newUser.assignValidatedPassword(passwordFactory.create(infoDTO.password),
                passwordFactory.create(infoDTO.passwordConfirmation));
        newUser.assignPhoto(photoFactory.create(infoDTO.photo));
        newUser.assignProfileId(visitorId);

        return newUser;
    }
}