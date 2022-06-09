package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import switch2021.project.utils.ValueObject;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Embeddable
@NoArgsConstructor
@ToString
@Getter
public class ResourceIDReeng implements ValueObject<ResourceIDReeng> {

    @Embedded
    private UserID user;
    @Embedded
    private ProjectID project;
    private LocalDate startDate;

        public ResourceIDReeng(UserID user, ProjectID project, LocalDate startDate) {
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

    @Override
    public String toString() {
        return  user + "&" +
                project + "&" +
                startDate;
    }
}
