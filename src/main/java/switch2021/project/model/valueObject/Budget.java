package switch2021.project.model.valueObject;

import lombok.Getter;
import java.util.Objects;

@Getter
public class Budget {

    /**
     * Attributes
     */
    private double budgetP;


    /**
     * Constructor
     */
    public Budget (double budgetP) {
        if (budgetP <= 0)
            throw new IllegalArgumentException("Budget field cannot be under or equal to 0.");
        this.budgetP = budgetP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Budget)) return false;
        Budget budget1 = (Budget) o;
        return Double.compare(budget1.budgetP, budgetP) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetP);
    }
}
