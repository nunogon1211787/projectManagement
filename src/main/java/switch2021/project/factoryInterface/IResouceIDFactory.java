package switch2021.project.factoryInterface;

import switch2021.project.model.Resource.ResourceIDReeng;

public interface IResouceIDFactory {
    ResourceIDReeng create(String systemUserID, String projectID, String startDate);
}
