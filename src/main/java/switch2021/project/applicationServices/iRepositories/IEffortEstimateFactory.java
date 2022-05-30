package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.valueObjects.vos.EffortEstimate;

public interface IEffortEstimateFactory {
    EffortEstimate create(double effort);
}
