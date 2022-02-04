package switch2021.project.stores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Typology;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyStoreTest {

   /** Attribute **/
    private Company company;
    private TypologyStore typologyStore;

    @BeforeEach
    public void initialize() {
        company = new Company();
        typologyStore = company.getTypologyStore();
    }

    @Test
    public void typologyStoreTest() {
        //Arrange
        TypologyStore test = new TypologyStore();
        //Assert
        assertEquals(test.getTypologyList().size(), 0);
        assertNotEquals(test, typologyStore.getTypologyList());
    }

    @Test
    public void populateTypologyList() {
        //Arrange
        TypologyStore test = new TypologyStore();
        Typology tes1 = new Typology("Fixed Cost");
        Typology tes2 = new Typology("Time and Materials");
        //Act
        test.populateDefault();
        //Assert
        assertEquals(test.getTypologyList().size(), 2);
        assertEquals(test.getTypologyList(), typologyStore.getTypologyList());
        assertEquals(tes1, test.getTypology(1));
        assertEquals(tes2, test.getTypology(2));
    }

    @Test
    public void createTypologyTestWhitEmptyDescription(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange and act
            typologyStore.createTypology("");
        });
    }

    /**
     * Este método faz teste do construtor de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology()
     **/
    @Test
    public void createTypologyTest() {
        //Arrange
        Typology newTypo = company.getTypologyStore().createTypology( "Fixed Cost");
        //Act
        Typology expected = company.getTypologyStore().getTypologyList().get(0);
        //Assert
        assertEquals(expected,newTypo);
    }

    @Test
    public void idGeneratorTest(){
        //Arrange
        Typology newTypo = typologyStore.createTypology("Test1");
        //Act
        typologyStore.saveTypology(newTypo);
        Typology newTypo2 = typologyStore.createTypology("Test2");
        //Assert
        assertEquals(newTypo.getId_Typology(), 3);
        assertEquals(newTypo2.getId_Typology(),0); //ID is 0 when initialized.
    }

    @Test //Test for adding new typology not null at Typology List checking attributes and list size.
    public void saveTypologyTestNotNull() {
        //Arrange
        Typology typo2 = new Typology("Test");
        //Act
        typologyStore.saveTypology(typo2);
        //Assert
        assertEquals("Test", typo2.getDescription());
        assertEquals(3,typo2.getId_Typology());
        assertEquals(3, typologyStore.getTypologyList().size());
    }

    @Test //Test for adding new null typology at Typology List checking attributes and list size.
    public void saveTypologyTestNull() {
        //Assert
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

        String description = typo3.getDescription();
        String value_description = "TestTypology3";

        //Assert
        assertEquals(description, value_description);
        assertEquals(7,typologyStore.getTypologyList().size());
    }

    @Test
    public void removeTypologyTest() {
        //Arrange
        Typology typo2 = new Typology("TestTypology2");
        Typology typo3 = new Typology("TestTypology3");
        typologyStore.saveTypology(typo2);
        typologyStore.saveTypology(typo3);
        //Act and Assert
        assertEquals(4,typologyStore.getTypologyList().size());
        typologyStore.removeTypology(typologyStore.getTypology(1));
        assertEquals(3,typologyStore.getTypologyList().size());
    }

    @Test
    public void removeTypologyInexistent() {
        //Act
        assertFalse(typologyStore.removeTypology(typologyStore.getTypology(4)));
    }

    @Test
    public void getTypologyWithDescriptionTest(){
        //Arrange //Act
        Typology descriptionTest = typologyStore.getTypology("Time and Materials");
        //Assert
        assertEquals(descriptionTest, new Typology("Time and Materials"));
    }

    @Test
    public void getTypologyWithWrongDescriptionTest(){
        //Arrange //Act
        Typology descriptionTest = typologyStore.getTypology("time and materials");
        //Assert
        assertNull(descriptionTest);
    }

    @Test
    public void getTypologyWithId_TypologyTest(){
        //Arrange //Act
        Typology descriptionTest = typologyStore.getTypology(2);
        //Assert
        assertEquals(descriptionTest, new Typology("Time and Materials"));
    }

    @Test
    public void getWrongId_TypologyTest(){
        //Arrange //Act
        Typology id = typologyStore.getTypology(10);
        //Assert
        assertNull(id);
    }


    @Test
    public void getTypologyListTest() {
        //Arrange
        TypologyStore test = new TypologyStore();
        //Act
        test.populateDefault();
        //Assert
        assertEquals(typologyStore.getTypologyList(), test.getTypologyList());
    }





    @Test
    public void validateTypology(){
        //Arrange
        Typology typo = new Typology("test");
        Typology typo1 = new Typology("Tes");
        Typology typo2 = new Typology("T");
        //Assert
        assertTrue(typologyStore.validateTypology(typo));
        assertTrue(typologyStore.validateTypology(typo1));
        assertTrue(typologyStore.validateTypology(typo2));

    }

    @Test
    public void thisNotExistTest() {
        //Arrange
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
        list1.saveTypology(list1.createTypology("new"));
        TypologyStore  list2 = new TypologyStore ();
        list2.saveTypology(list1.createTypology("new"));
        TypologyStore  list3 = new TypologyStore ();
        list3.saveTypology(list3.createTypology("not new"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}
