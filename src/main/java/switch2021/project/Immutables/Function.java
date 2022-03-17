package switch2021.project.Immutables;

import java.util.Objects;


public class Function {

    /*** Attributes **/
    private final String text;
    private final int MIN_DESCRIPTION_LENGTH = 1;
    private final int MAX_DESCRIPTION_LENGTH = 20;

    /*** Constructor **/
    public Function (String function) {
        String x = " characters";
        if (function == null)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + x);
        if (function.trim().length() < MIN_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + x);
        if (function.trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAX_DESCRIPTION_LENGTH + x);
        this.text = function;
    }

    /*** Getter **/
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(text, function.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH);
    }
}
