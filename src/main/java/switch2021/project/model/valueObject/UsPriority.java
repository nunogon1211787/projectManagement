package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter


public class UsPriority implements ValueObject<UsPriority> {

    /**
     * Attributes
     **/
    private final int priorityUs;
    private static final int MINPRIORITY = 0;
    private static final int MAXPRIORITY = 5;


    /**
     * Constructor
     **/
    public UsPriority(int priorityUs) {
        isValidPriority(priorityUs);
        this.priorityUs = priorityUs;
    }

    /**
     * Methods
     **/
    private void isValidPriority(int usPriority) {
        if (usPriority < MINPRIORITY || usPriority > MAXPRIORITY) {
            throw new IllegalArgumentException("Check priority, cannot be < 0 or superior to 5");
        }
    }

    /**
     * Override
     **/
    @Override
    public boolean sameValueAs(final UsPriority other) {
        return other !=null && this.priorityUs==(other.priorityUs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       final UsPriority that = (UsPriority) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priorityUs);
    }
}
