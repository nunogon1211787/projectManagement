package switch2021.project.model.valueObject;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class ProjectCode {

    /**
     * Attributes
     **/
    private final String code;

    /**
     * Constructor
     **/
    public ProjectCode(int x) {
        if (x <= 0)
            throw new IllegalArgumentException("Project code number cannot be negative");


        this.code = "Project_" + LocalDate.now().getYear() + "_" + x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCode that = (ProjectCode) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
