package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Email;

@Service
public class EmailFactory implements IValueObjectsFactory<Email> {
    @Override
    public Email create(Object o) {
        return new Email(o.toString());
    }
}