package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsPriority that = (UsPriority) o;
        return usPriority == that.usPriority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usPriority, MIN_PRIORITY, MAX_PRIORITY);
    }
}
