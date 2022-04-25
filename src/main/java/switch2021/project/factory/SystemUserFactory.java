package switch2021.project.factory;

import switch2021.project.factoryInterface.SystemUserFactoryInterface;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileId;

public class SystemUserFactory implements SystemUserFactoryInterface {
    @Override
    public SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfileId visitorId) {
        return new SystemUser(userName, email, function, password, passwordConfirmation, photo, visitorId);
    }
}
