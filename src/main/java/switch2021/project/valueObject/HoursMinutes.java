package switch2021.project.valueObject;

import lombok.Getter;


@Getter
public class HoursMinutes {

    /**
      * Attributes
     **/

    private final int effortHours;
    private final int effortMinutes;
    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 23;
    private static final int MIN_MINUTE = 0;
    private static final int MAX_MINUTE = 60;

    /**
     * Constructor (without SINGLETON)
     **/

    public HoursMinutes(int effortHours, int effortMinutes) {
        checkWorkTimeRules(effortHours,effortMinutes);
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
    }

    /**
     * Methods
     **/

    private void checkWorkTimeRules(double effortHours, double effortMinutes) {
        if (effortHours < MIN_HOUR || effortMinutes < MIN_MINUTE || effortHours == MIN_HOUR && effortMinutes == MIN_MINUTE
                || effortHours > MAX_HOUR || effortMinutes >= MAX_MINUTE)
            throw new IllegalArgumentException("Not valid work time values." + " Minute interval: [" + MIN_MINUTE + " - " + MAX_MINUTE + "]" +
                    " || Hour interval: [" + MIN_HOUR + " - " + MAX_HOUR + "]");
    }
}
