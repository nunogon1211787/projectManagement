package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IEmailFactory;
import switch2021.project.model.valueObject.Email;

@Service
public class EmailFactory implements IEmailFactory {
    @Override
    public Email createEmail(String emailText) {
        return new Email(emailText);
    }
}