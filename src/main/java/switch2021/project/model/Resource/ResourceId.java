package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserId;

@Getter
public class ResourceId {

    private final SystemUserId user;
    private final ProjectID project;

    public ResourceId(SystemUserId user, ProjectID project) {
        this.user = user;
        this.project = project;
    }
}
