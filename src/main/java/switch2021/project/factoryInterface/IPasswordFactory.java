package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.Password;

public interface IPasswordFactory {
    Password createPassword(String password);
}
