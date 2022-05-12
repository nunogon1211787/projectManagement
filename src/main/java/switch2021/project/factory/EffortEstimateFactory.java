package switch2021.project.factory;


import org.springframework.stereotype.Component;
import switch2021.project.interfaces.IEffortEstimateFactory;
import switch2021.project.model.valueObject.EffortEstimate;

@Component
public class EffortEstimateFactory implements IEffortEstimateFactory {

    @Override
    public EffortEstimate create(double effort) {
        return null;
    }
}
