package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyStoreTest {

    private Company company;

    @BeforeEach
    public void initialize() {
        company = new Company();
        company.getTypologyStore();

    }

    @Test
    public void createTypologyTestNull() {
        //Arrange
        TypologyStore Test = new TypologyStore();
        //Act
        Typology typo = Test.createTypology(0, "");
        //Assert
        assertFalse(Test.saveTypology(typo));
    }

    /**
     * Este método faz teste do construtor de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology()
     **/
    @Test
    public void constructorTypologyTest() {
        //Arrange
        Typology newTypo = company.getTypologyStore().createTypology(0, "Fixed Cost");
        //Act
        Typology expected = company.getTypologyStore().getTypologyList().get(0);
        //Assert
        assertEquals(expected,newTypo);

    }

    @Test
    public void getArrayTypologyTest() {
        //Arrange
        List<Typology> arrayTest = company.getTypologyStore().getTypologyList();
        //Act
        Typology typo = company.getTypologyStore().createTypology(0, "Test");
        company.getTypologyStore().saveTypology(typo);
        arrayTest.add(typo);
        //Assert
        assertEquals(company.getTypologyStore().getTypologyList(), arrayTest);
    }

    @Test
    public void getTypologyByIdTest() {
        //Arrange
        //Act
        Typology typo = company.getTypologyStore().getTypologyById(0);
        //Assert
        assertEquals(typo, company.getTypologyStore().getTypologyById(0));

    }

    @Test
    public void validateTypology(){
        //Arrange
        Typology typo = new Typology(0, "");
        Typology typo1 = new Typology(0, "Tes");
        Typology typo2 = new Typology(0, "");
        //Act
        company.getTypologyStore().add(typo);
        company.getTypologyStore().add(typo1);
        company.getTypologyStore().add(typo2);
        //Assert
        assertFalse(company.getTypologyStore().validateTypology(typo));
        assertTrue(company.getTypologyStore().validateTypology(typo1));
        assertFalse(company.getTypologyStore().validateTypology(typo2));
        assertEquals(typo1,company.getTypologyStore().getTypologyById(2));

    }

    @Test
    public void saveTypologyTest() {
        //Arrange
        Typology typo = new Typology(0, "Test");
        //Act
        company.getTypologyStore().saveTypology(typo);
        int size = company.getTypologyStore().getTypologyList().size();
        //Assert
        assertEquals(3,size);
    }

    @Test
    public void notSaveTypologyTest() {
        //Arrange
        Typology typo = new Typology(0, "");
        //Act
        company.getTypologyStore().saveTypology(typo);
        int size = company.getTypologyStore().getTypologyList().size();
        //Assert
        assertEquals(2,size);
    }
}
