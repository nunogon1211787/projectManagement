package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IEmailFactory;
import switch2021.project.model.valueObject.Email;

@Component
public class EmailFactory implements IEmailFactory {
    @Override
    public Email createEmail(String emailText) {
        return new Email(emailText);
    }
}