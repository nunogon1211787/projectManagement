package switch2021.project.valueObject;

import lombok.Getter;

@Getter
public class Minutes {

    /**
     * Attributes
     **/

    private final int effortMinutes;
    private final int MIN_MINUTE = 0;
    private final int MAX_MINUTE = 60;

    /**
     * Constructor (without SINGLETON)
     **/

    public Minutes(int effortMinutes){
        checkMinutesTimeRules(effortMinutes);
        this.effortMinutes = effortMinutes;
    }

    /**
     * Methods
     **/

    private void checkMinutesTimeRules ( int effortMinutes){
        if (effortMinutes < MIN_MINUTE  || effortMinutes >= MAX_MINUTE)
            throw new IllegalArgumentException("Not valid work time values." + " Minute interval: [" + MIN_MINUTE + " - " + MAX_MINUTE + "]");
    }
}
