package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.factoryInterface.IResouceIDFactory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.utils.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class ResourceIDReeng implements ValueObject<ResourceIDReeng> {

        private final SystemUserID user;
        private final ProjectID project;
        private final LocalDate startDate;

        public ResourceIDReeng(SystemUserID user, ProjectID project, LocalDate startDate) {
            this.user = user;
            this.project = project;
            this.startDate = startDate;
        }

    @Override
    public boolean sameValueAs(ResourceIDReeng other) {
        return other != null &&
                this.user == other.user &&
                this.project == other.project &&
                this.startDate == other.startDate ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceIDReeng)) return false;
        ResourceIDReeng that = (ResourceIDReeng) o;
        return Objects.equals(getUser(), that.getUser()) && Objects.equals(getProject(), that.getProject()) && Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getProject(), getStartDate());
    }
}
