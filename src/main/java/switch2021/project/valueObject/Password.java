package switch2021.project.valueObject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Password {

    /** Attributes **/
    private final String pwd;

    /** Constructor **/
    public Password (String pwd) {
        checkPwdSize(pwd);
        checkNumberPresence(pwd);
        checkUpperCasePresence(pwd);
        checkLowerCasePresence(pwd);
        this.pwd = encryptPassword(pwd);
    }

    public void checkPwdSize(String pwd){
        if(pwd.length() < 8){
            throw new IllegalArgumentException("Password too short.");
        }
    }

    public void checkNumberPresence(String pwd){
        int count = 0;
        char[] chars = pwd.toCharArray();
        for(char c : chars) {
            if (Character.isDigit(c)) {
                count = count + 1;
            }
        }
        if(count == 0){
                throw new IllegalArgumentException("Password should contain at least 1 number");
        }
    }

    public void checkUpperCasePresence(String pwd){
        int count = 0;
        char[] chars = pwd.toCharArray();
        for(char c : chars) {
            if (Character.isUpperCase(c)) {
                count = count + 1;
            }
        }
        if(count == 0){
            throw new IllegalArgumentException("Password should contain at least 1 upper case");
        }
    }

    public void checkLowerCasePresence(String pwd){
        int count = 0;
        char[] chars = pwd.toCharArray();
        for(char c : chars) {
            if (Character.isLowerCase(c)) {
                count = count + 1;
            }
        }
        if(count == 0){
            throw new IllegalArgumentException("Password should contain at least 1 lower case");
        }
    }

    public String encryptPassword(String password) {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) + 99;
            stringBuilder.append((char) codigoASCII);
        }
        return stringBuilder.toString();
    }

    public String decryptPassword(String password) {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) - 99;
            stringBuilder.append((char) codigoASCII);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(pwd, password.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pwd);
    }
}
