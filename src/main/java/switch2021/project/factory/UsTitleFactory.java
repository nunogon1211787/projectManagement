package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.UsTitle;

@Component
public class UsTitleFactory implements IValueObjectsFactory<UsTitle> {
    @Override
    public  UsTitle create (Object o){
        return new UsTitle(o.toString());
    }
}
