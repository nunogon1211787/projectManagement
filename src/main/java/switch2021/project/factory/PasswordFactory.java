package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IPasswordFactory;
import switch2021.project.model.valueObject.Password;

@Service
public class PasswordFactory implements IPasswordFactory {
    @Override
    public Password createPassword(String password) {
        return new Password(password);
    }
}