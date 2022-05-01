package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;

import java.time.LocalDate;

@Getter
public class ResourceId {

    private final SystemUserID user;
    private final ProjectID project;
    private final LocalDate startDate;

    public ResourceId(SystemUserID user, ProjectID project, LocalDate startDate) {
        this.user = user;
        this.project = project;
        this.startDate = startDate;
    }
}
