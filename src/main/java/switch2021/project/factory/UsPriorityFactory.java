package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.UsPriority;

@Component
public class UsPriorityFactory implements IValueObjectsFactory<UsPriority> {
    @Override
    public UsPriority create(Object o){
        return new UsPriority((Integer) o);
    }

}
