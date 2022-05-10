package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.SystemUserID;

public interface ISystemUserIDFactory {
    SystemUserID createSystemUserID (String emailText);
}
