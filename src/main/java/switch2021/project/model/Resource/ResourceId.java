package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserId;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
public class ResourceId implements ValueObject<ResourceId> {

    private final SystemUserId user;
    private final ProjectID project;

    public ResourceId(SystemUserId user, ProjectID project) {
        this.user = user;
        this.project = project;
    }

    @Override
    public boolean sameValueAs(ResourceId other) {
        return other.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceId that = (ResourceId) o;
        return Objects.equals(user, that.user) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project);
    }
}
