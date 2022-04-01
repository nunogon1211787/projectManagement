package switch2021.project.model.valueObject;

import lombok.Getter;

@Getter
public class PercentageOfAllocation {

    /**
     * Attributes
     **/
    private double percentage;
    private static final double MINPERCENTAGE = 0.0;
    private static final double MAXPERCENTAGE = 1.0;

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
        if (percOfAllo < MINPERCENTAGE || percOfAllo > MAXPERCENTAGE) {
            throw new IllegalArgumentException("Check percentage, cannot be < 0 or superior to 1");
        }
    }
}
