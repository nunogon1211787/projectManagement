package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Budget {

    /**
     * Attributes
     */
    private double budget;


    /**
     * Constructor
     */
    public Budget (double budget) {
        if (budget < 0)
            throw new IllegalArgumentException("Budget field cannot be under 0.");
        if (budget == 0)
            throw new IllegalArgumentException("Budget field cannot be 0.");
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Budget)) return false;
        Budget budget1 = (Budget) o;
        return Double.compare(budget1.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }
}
