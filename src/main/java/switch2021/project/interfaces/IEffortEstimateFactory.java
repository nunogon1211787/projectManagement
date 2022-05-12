package switch2021.project.interfaces;

import switch2021.project.model.valueObject.EffortEstimate;

public interface IEffortEstimateFactory {
    EffortEstimate create(double effort);
}
