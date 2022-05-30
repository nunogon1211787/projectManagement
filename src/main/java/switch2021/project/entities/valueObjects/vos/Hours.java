package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

@Getter
public class Hours {

    /**
     * Attributes
     **/

    private final int effortHours;
    private static final int MINHOUR = 0;
    private static final int MAXHOUR = 23;

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
        if (effortHours < MINHOUR || effortHours > MAXHOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MINHOUR + " - " + MAXHOUR + "]");
    }
}
