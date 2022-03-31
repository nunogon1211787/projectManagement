package switch2021.project.model.valueObject;

import lombok.Getter;

@Getter
public class Hours {

    /**
     * Attributes
     **/

    private final int effortHours;
    private final int MIN_HOUR = 0;
    private final int MAX_HOUR = 23;

    /**
     * Constructor (without SINGLETON)
     **/

    public Hours(int effortHours) {
        checkWorkTimeRules(effortHours);
        this.effortHours = effortHours;
    }

    /**
     * Methods
     **/

    private void checkWorkTimeRules(int effortHours) {
        if (effortHours < MIN_HOUR || effortHours > MAX_HOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MIN_HOUR + " - " + MAX_HOUR + "]");
    }
}
