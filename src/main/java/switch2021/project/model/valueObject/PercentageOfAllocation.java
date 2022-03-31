package switch2021.project.model.valueObject;

import lombok.Getter;

@Getter
public class PercentageOfAllocation {

    /**
     * Attributes
     **/
    private double percentage;
    private final double MIN_PERCENTAGE = 0.0;
    private final double MAX_PERCENTAGE = 1.0;

    /**
     * Constructor
     **/
    public PercentageOfAllocation(double percOfAllo) {
        isValidPercentage(percOfAllo);
        this.percentage = percOfAllo;
    }

    /**
     * Methods
     **/
    private void isValidPercentage(double percOfAllo) {
        if (percOfAllo < MIN_PERCENTAGE || percOfAllo > MAX_PERCENTAGE) {
            throw new IllegalArgumentException("Check percentage, cannot be < 0 or superior to 1");
        }
    }
}
