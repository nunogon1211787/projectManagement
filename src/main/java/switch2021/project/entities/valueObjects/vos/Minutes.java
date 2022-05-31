package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

@Getter
public class Minutes {

    /**
     * Attributes
     **/

    private final int effortMinutes;
    private static final int MINMINUTE = 0;
    private static final int MAXMINUTE = 60;

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
        if (effortMinutes < MINMINUTE || effortMinutes >= MAXMINUTE)
            throw new IllegalArgumentException("Not valid work time values." + " Minute interval: [" + MINMINUTE + " - " + MAXMINUTE + "]");
    }
}
