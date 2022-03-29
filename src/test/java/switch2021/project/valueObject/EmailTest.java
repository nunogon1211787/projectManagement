package switch2021.project.valueObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void NullTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
           //Arrange
            String test = null;
            //Act
            new Email(test);
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void emailEmptyOrBlankTest(String x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange and Act
            new Email(x);
        });
    }

    @Test
    void withoutAtSignTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withAtSignFirstPositionTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "@123test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withAtSignLastPositionTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123test.pt@";
            //Act
            new Email(test);
        });
    }

    @Test
    void withoutDotAfterAtSignTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"!", "#", "$", "%", "&", "*", "(", ")", "'", "+", ",", "/", ":", ";", "<", "=", ">", "?", "[", "]", "^", "`", "{", "|", "}"})
    void notAllowedSpecialCharactersTest(String x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "1" + x +"23@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 51, 60})
    void lengthRulesBeforeAtSignFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String local = new String(data);
            String test = local + "@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 51, 60})
    void lengthRulesAfterAtSignFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String domain = new String(data);
            String test = "123@" + domain + ".pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 10})
    void lengthRulesLastPartFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String lastPart = new String(data);
            String test = "123@test." + lastPart;
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = c + "123@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123" + c + "@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstAfterAtSignBeforeDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@" + c + "test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastAfterAtSignBeforeDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test" + c + ".pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstAfterAtSignAfterDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test." + c + "pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastAfterAtSignAfterDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test.pt" + c;
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"..","._",".-", "__", "_-", "--"})
    void twoConsecutiveDotsBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12" + c + "3@test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void twoConsecutiveDotsAfterAtSignTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@te..st.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 50})
    void createEmailWithLengthRulesBeforeAtSighSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String local = new String(data);
        //Act
        String text = local + "@test.pt";
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 50})
    void createEmailWithLengthRulesAfterAtSighSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String domain = new String(data);
        //Act
        String text = "123@" + domain + ".pt";
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    void lengthRulesLastPartSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String lastPart = new String(data);
        //Act
        String text = "123@test." + lastPart ;
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @Test
    void withTwoAtSign(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12@3@test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withFourAtSign(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12@3@te@st.p@t";
            //Act
            new Email(test);
        });
    }

    @Test
    void checkDifferentEmails(){
        //Arrange
        String text1 = "123@test.pt";
        String text2 = "1234@test.pt";
        //Act
        Email test = new Email(text1);
        //Assert
        assertFalse(test.hasEmail(text2));
    }

}