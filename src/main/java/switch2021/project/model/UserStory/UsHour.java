package switch2021.project.model.UserStory;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UsHour {


    /**
     * Attributes
     **/

    private final int usHours;
    private static final int MINHOUR = 0;

    /**
     * Constructor (without SINGLETON)
     **/

    public UsHour(int usHours) {
        checkWorkTimeRules(usHours);
        this.usHours = usHours;
    }

    /**
     * Methods
     **/

    private void checkWorkTimeRules(int usHours) {
        if (usHours < MINHOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MINHOUR + "]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsHour usHour = (UsHour) o;
        return usHours == usHour.usHours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usHours);
    }
}
