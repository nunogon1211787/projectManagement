package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.Email;

public interface IEmailFactory {
    Email createEmail(String emailText);
}
