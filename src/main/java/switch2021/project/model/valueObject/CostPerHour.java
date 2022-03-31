package switch2021.project.model.valueObject;

import lombok.Getter;

@Getter
public class CostPerHour {

    /**
     * Attributes
     **/

    private double cost;
    private final double MIN_COST = 0.0;

    /**
     * Constructor
     **/
    public CostPerHour(double costPerHour) {
        isValidCostPerHour(costPerHour);
        this.cost = costPerHour;
    }

    /**
     * Methods
     **/

    private void isValidCostPerHour(double costPerHour) {
        if (costPerHour < MIN_COST) {
            throw new IllegalArgumentException("Check cost per hour, cannot be < 0");
        }
    }
}
