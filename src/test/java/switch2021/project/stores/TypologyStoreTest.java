package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.model.Company;
import switch2021.project.model.Typology;
import static org.junit.jupiter.api.Assertions.*;

public class TypologyStoreTest {

   /** Attribute **/
    private Company company;
    private TypologyStore typologyStore;

    @DisplayName("Test typology list and equal to a different list")
    @Test
    public void typologyStoreTest() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        TypologyStore test = new TypologyStore();
        //Assert
        assertEquals( 0, test.getTypologyList().size());
        assertNotEquals(test, typologyStore.getTypologyList());
    }

    @Test
    public void populateTypologyList() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        TypologyStore test = new TypologyStore();
        Typology tes1 = new Typology("Fixed Cost");
        Typology tes2 = new Typology("Time and Materials");
        //Act
        test.populateDefault();
        //Assert
        assertEquals( 2,test.getTypologyList().size());
        assertEquals(test.getTypologyList(), typologyStore.getTypologyList());
        assertEquals(tes1, test.getTypologyByDescription("Fixed Cost"));
        assertEquals(tes2, test.getTypologyByDescription("Time and Materials"));
    }

    @Test
    public void createTypologyTestWhitEmptyDescription(){
        //Assert
        company = new Company();
        typologyStore = company.getTypologyStore();
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange and act
            typologyStore.createTypology("");
        });
    }

    /**
     * Este método faz teste do construtor de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology()
     **/

    @Test //Test for adding new typology not null at Typology List checking attributes and list size.
    public void saveTypologyTestNotNull() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        Typology typo2 = new Typology("Test");
        //Act
        typologyStore.saveTypology(typo2);
        //Assert
        assertEquals("Test", typo2.getDescription().getText());
        assertEquals(3, typologyStore.getTypologyList().size());
    }

    @Test //Test for adding new null typology at Typology List checking attributes and list size.
    public void saveTypologyTestNull() {
        //Assert
        company = new Company();
        typologyStore = company.getTypologyStore();
        assertThrows (IllegalArgumentException.class, () -> {
            //Arrange
            Typology typo2 = new Typology("");
            //Act
            typologyStore.saveTypology(typo2);
        });
    }

    @Test
    public void addMultiplesTypologiesAtSameTime() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        Typology typo2 = new Typology("TestTypology2");
        Typology typo3 = new Typology("TestTypology3");
        Typology typo4 = new Typology("TestTypology4");
        Typology typo5 = new Typology("TestTypology5");
        Typology typo6 = new Typology("TestTypology6");
        //Act
        typologyStore.saveTypology(typo2);
        typologyStore.saveTypology(typo3);
        typologyStore.saveTypology(typo4);
        typologyStore.saveTypology(typo5);
        typologyStore.saveTypology(typo6);

        Description description = typo3.getDescription();
        Description value_description = new Description("TestTypology3");

        //Assert
        assertEquals(description, value_description);
        assertEquals(7,typologyStore.getTypologyList().size());
    }

    @Test
    public void getTypologyWithDescriptionTest(){
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        // Act
        Typology descriptionTest = typologyStore.getTypologyByDescription("Time and Materials");
        //Assert
        assertEquals(descriptionTest, new Typology("Time and Materials"));
    }

    @Test
    public void getTypologyWithWrongDescriptionTest(){
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        // Act
        Typology descriptionTest = typologyStore.getTypologyByDescription("time and materials");
        //Assert
        assertNull(descriptionTest);
    }

    @Test
    public void getTypologyListTest() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        TypologyStore test = new TypologyStore();
        //Act
        test.populateDefault();
        //Assert
        assertEquals(typologyStore.getTypologyList(), test.getTypologyList());
    }

    @Test
    public void validateTypology(){
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        Typology typo = new Typology("test");
        Typology typo1 = new Typology("Tes");
        Typology typo2 = new Typology("Test2");
        //Assert
        assertTrue(typologyStore.validateTypology(typo));
        assertTrue(typologyStore.validateTypology(typo1));
        assertTrue(typologyStore.validateTypology(typo2));

    }

    @Test
    public void thisNotExistTest() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
        Typology typo = new Typology("Test");
        //Act
        company.getTypologyStore().saveTypology(typo);
        int size = company.getTypologyStore().getTypologyList().size();
        //Assert
        assertEquals(3,size);
    }

    @Test
    public void thisExistTest() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            company = new Company();
            typologyStore = company.getTypologyStore();
            //Arrange
            Typology typo = new Typology("teste");
            Typology typo1 = new Typology("teste");
            //Act
            typologyStore.saveTypology(typo);
            typologyStore.saveTypology(typo1);
        });
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        TypologyStore list1 = new TypologyStore ();
//        list1.createTypology(list1.createTypology("new"));
        list1.createTypology("new");
        TypologyStore  list2 = new TypologyStore ();
//        list2.saveTypology(list1.createTypology("new"));
        list2.createTypology("new");
        TypologyStore  list3 = new TypologyStore ();
//        list3.saveTypology(list3.createTypology("not new"));
        list3.createTypology("not new");
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}
