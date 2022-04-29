package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;

@Getter
public class ResourceId {

    private final SystemUserID user;
    private final ProjectID project;

    public ResourceId(SystemUserID user, ProjectID project) {
        this.user = user;
        this.project = project;
    }
}
