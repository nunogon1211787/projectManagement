package switch2021.project.valueObject;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Date {

    /**
     * Attributes
     **/

    private LocalDate effortDate;


    /**
     * Constructor (without SINGLETON)
     **/
    public Date(LocalDate effortDate) {
        checkWorkDateRules(effortDate);
        this.effortDate = effortDate;
    }


    /**
     * Methods
     **/
    private void checkWorkDateRules(LocalDate effortDate) {
        if (effortDate == null) {
            this.effortDate = LocalDate.now();
        } else if (effortDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else {
            this.effortDate = effortDate;
        }
    }
}
