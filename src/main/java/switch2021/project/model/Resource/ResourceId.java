package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserId;

import java.time.LocalDate;

@Getter
public class ResourceId {

    private final SystemUserId user;
    private final ProjectID project;
    private final LocalDate startDate;

    public ResourceId(SystemUserId user, ProjectID project, LocalDate startDate) {
        this.user = user;
        this.project = project;
        this.startDate = startDate;
    }
}
