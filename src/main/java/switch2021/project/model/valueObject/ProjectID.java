package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.utils.ValueObject;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class ProjectID implements ValueObject<ProjectID> {

    /**
     * Attributes
     **/
    private String code;

    /**
     * Constructor
     **/
    public ProjectID(String x) {

        this.code = "Project_" + LocalDate.now().getYear() + "_" + x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectID that = (ProjectID) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean sameValueAs(ProjectID other) {
        return false;
    }
}
