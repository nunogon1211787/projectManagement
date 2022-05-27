package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IEmailFactory;
import switch2021.project.factoryInterface.IUserIDFactory;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.SystemUserID;

@Component
public class UserIDFactory implements IUserIDFactory {

    @Autowired
    private IEmailFactory emailFactory;

    @Override
    public SystemUserID createUserID(String emailText) {
        Email email = emailFactory.createEmail(emailText);
        return new SystemUserID(email);
    }
}
