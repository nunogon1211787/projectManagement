package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.UsHour;

@Component
public class UsHourFactory implements IValueObjectsFactory<UsHour> {

    @Override
    public UsHour create(Object o) {
        return new UsHour((Double) o);

    }
}
