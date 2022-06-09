package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;

public interface IResourceIDFactory {
    ResourceIDReeng create(String systemUserID, String projectID, String startDate);
}
