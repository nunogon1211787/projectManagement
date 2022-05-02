package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Password;

@Service
public class PasswordFactory implements IValueObjectsFactory<Password> {
    @Override
    public Password create(Object o) {
        return new Password(o.toString());
    }
}
