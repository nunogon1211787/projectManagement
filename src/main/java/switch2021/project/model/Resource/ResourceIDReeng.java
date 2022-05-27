package switch2021.project.model.Resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import switch2021.project.factoryInterface.IResouceIDFactory;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import switch2021.project.model.valueObject.Date;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@ToString
@Getter
@Embeddable
@NoArgsConstructor
public class ResourceIDReeng implements ValueObject<ResourceIDReeng> {

    @Embedded
    private SystemUserID user;
    @Embedded
    private ProjectID project;

//    @Embedded
//    @Temporal(TemporalType.DATE)
//    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate startDate;

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
