package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IPasswordFactory;
import switch2021.project.model.valueObject.Password;

@Component
public class PasswordFactory implements IPasswordFactory {
    @Override
    public Password createPassword(String password) {
        return new Password(password);
    }
}
