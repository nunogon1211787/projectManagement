package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class SprintDuration {

    /**
     * Attribute
     **/
    private final int sprintDurationDays;

    /**
     * Constants
     **/
    int MIN = 7;
    int MAX = 31;

    /**
     * Constructor (without SINGLETON)
     **/
    public SprintDuration (int sprintDurationDays) {
        checkRules(sprintDurationDays);
        this.sprintDurationDays = sprintDurationDays;
    }

    private void checkRules(int sprintDuration) {
        if(sprintDuration < MIN || sprintDuration > MAX) {
            throw new NullPointerException("The sprint duration must be within " + MIN + " and " +  MAX + " days");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDuration that = (SprintDuration) o;
        return sprintDurationDays == that.sprintDurationDays && MIN == that.MIN && MAX == that.MAX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintDurationDays, MIN, MAX);
    }
}
