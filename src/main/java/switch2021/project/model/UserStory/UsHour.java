package switch2021.project.model.UserStory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.utils.ValueObject;


@EqualsAndHashCode
@Getter
public class UsHour implements ValueObject<UsHour> {


    /**
     * Attributes
     **/
    private final double usHours;
    private static final int MINHOUR = 0;

    /**
     * Constructor
     **/
    public UsHour(double usHours) {
        checkWorkTimeRules(usHours);
        this.usHours = usHours;
    }

    /**
     * Methods
     **/
    private void checkWorkTimeRules(double usHours) {
        if (usHours < MINHOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MINHOUR + "]");
    }

    /**
     * Override
     **/

    @Override
    public boolean sameValueAs(final UsHour other) {
        return other !=null && this.usHours==(other.usHours);
    }
}
