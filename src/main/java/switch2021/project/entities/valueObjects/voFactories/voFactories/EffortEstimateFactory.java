package switch2021.project.entities.valueObjects.voFactories.voFactories;


import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.IEffortEstimateFactory;
import switch2021.project.entities.valueObjects.vos.EffortEstimate;

@Component
public class EffortEstimateFactory implements IEffortEstimateFactory {

    @Override
    public EffortEstimate create(double effort) {
        return null;
    }
}
