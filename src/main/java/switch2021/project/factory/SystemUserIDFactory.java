package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.SystemUserID;

@Service
public class SystemUserIDFactory implements IValueObjectsFactory<SystemUserID> {

    @Autowired
    private final IValueObjectsFactory<Email> emailFactory;

    public SystemUserIDFactory(IValueObjectsFactory<Email> emailFactory) {
        this.emailFactory = emailFactory;
    }

    @Override
    public SystemUserID create(Object o) {
        Email email = emailFactory.create(o.toString());
        return new SystemUserID(email);
    }
}
