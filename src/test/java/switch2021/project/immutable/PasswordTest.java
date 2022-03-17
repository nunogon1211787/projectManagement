package switch2021.project.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

    @DisplayName("Password Success")
    @Test
    public void ValidPassword() {
        //Arrange
        Password pwd = new Password("Qwerty_1");
        String pwdRes = pwd.getPwd();
        //Assert
        assertEquals("´ÚÈÕ×ÜÂ\u0094", pwdRes);
    }

    @DisplayName("Password Fail - less 8")
    @Test
    public void PasswordFailLess8() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("Qwert_1");
        });
    }

    @DisplayName("Password Fail - no number")
    @Test
    public void PasswordFailNoNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("Qwerty_q");
        });
    }

    @DisplayName("Password Fail - no Upper Case")
    @Test
    public void PasswordFailNoUpperCase() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("qwerty_1");
        });
    }

    @DisplayName("Password Fail - no Lower Case")
    @Test
    public void PasswordFailNoLowerCase() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("QWERTY_1");
        });
    }

    @DisplayName("Encrypt Password Success")
    @Test
    public void EncryptPasswordSuccess() {
        //Arrange
        Password pwd = new Password("Qwerty_1");
        String pwdRes = pwd.getPwd();
        //Assert
        assertEquals("´ÚÈÕ×ÜÂ", pwdRes);
    }

    @DisplayName("Decrypt Password Success")
    @Test
    public void DecryptPasswordSuccess() {
        //Arrange
        Password pwd = new Password("Hellos_1");
        String pwdRes = pwd.decryptPassword(pwd.getPwd());
        //Assert
        assertEquals("Hellos_1", pwdRes);
    }
}