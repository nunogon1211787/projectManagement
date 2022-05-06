package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUsTitleFactory;
import switch2021.project.model.valueObject.UsTitle;

@Component
public class UsTitleFactory implements IUsTitleFactory {
    @Override
    public UsTitle create(String usTitle) {
        return new UsTitle(usTitle);
    }
}
