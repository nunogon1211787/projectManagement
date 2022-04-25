package switch2021.project.factoryInterface;

import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileId;

public interface SystemUserFactoryInterface {
    SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfileId visitorId);
}
