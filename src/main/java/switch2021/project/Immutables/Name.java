package switch2021.project.Immutables;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {

    /*** Attributes **/
    private final String nameF;


    /*** Constructor **/
    public Name(String name) {
        checkNameNotEmpty(name);
        checkIfNumber(name);
        checkIfSpecialChar(name);

        this.nameF = name;
    }

    /*** Getter **/
    public String getNameF() {
        return nameF;
    }

    /*** Methods **/
    public void checkNameNotEmpty(String name){
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
    }

    public void checkIfNumber(String name){
        char[] chars = name.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c)){
                throw new IllegalArgumentException("Name cannot contain any numbers");
    }}}

    public void checkIfSpecialChar(String name){
        Pattern p = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher m = p.matcher(name);
        boolean b = m.find();
        if (b)
            throw new IllegalArgumentException("Name cannot contain any special character");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(nameF, name.nameF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameF);
    }
}
