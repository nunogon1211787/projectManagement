package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class ProjectID implements ValueObject<ProjectID> {

    /**
     * Attributes
     **/
    private final String code;

    /**
     * Constructor
     **/
    public ProjectID(int x) {
        if (x <= 0)
            throw new IllegalArgumentException("Project code number cannot be negative");


        this.code = "Project_" + LocalDate.now().getYear() + "_" + x;
    }

    public ProjectID(String projectCode) {
        if (projectCode == null)
            throw new IllegalArgumentException("Project number cannot be null");
        this.code = projectCode;
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
