package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IEmailFactory;
import switch2021.project.factoryInterface.ISystemUserIDFactory;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.SystemUserID;

@Component
public class SystemUserIDFactory implements ISystemUserIDFactory {

    @Autowired
    private final IEmailFactory emailFactory;

    public SystemUserIDFactory(IEmailFactory emailFactory) {
        this.emailFactory = emailFactory;
    }

    @Override
    public SystemUserID createSystemUserID(String emailText) {
        Email email = emailFactory.createEmail(emailText);
        return new SystemUserID(email);
    }
}
