package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUsHourFactory;
import switch2021.project.model.valueObject.UsHour;

@Component
public class UsHourFactory implements IUsHourFactory {
    @Override
    public UsHour create(double timeEstimate) {
        return new UsHour(timeEstimate);

    }
}
