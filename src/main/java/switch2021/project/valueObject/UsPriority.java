package switch2021.project.valueObject;

import lombok.Getter;

@Getter
public class UsPriority {

    /**
     * Attributes
     **/

    private int usPriority;
    private final int MIN_PRIORITY = 0;
    private final int MAX_PRIORITY = 5;

    /**
     * Constructor
     **/
    public UsPriority(int usPriority) {
        isValidPriority(usPriority);
        this.usPriority = usPriority;
    }

    /**
     * Methods
     **/

    private void isValidPriority(int usPriority) {
        if (usPriority < MIN_PRIORITY || usPriority > MAX_PRIORITY) {
            throw new IllegalArgumentException("Check priority, cannot be < 0 or superior to 5");
        }
    }

}
