package switch2021.project.model.UserStory;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UsPriority {

    /**
     * Attributes
     **/
    private int priorityUs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsPriority that = (UsPriority) o;
        return priorityUs == that.priorityUs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(priorityUs, MINPRIORITY, MAXPRIORITY);
    }
}
