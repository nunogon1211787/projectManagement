package switch2021.project.model.UserStory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public class UsHour implements ValueObject<UsHour> {


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
    public boolean sameValueAs(UsHour other) {
        return false;
    }
}
