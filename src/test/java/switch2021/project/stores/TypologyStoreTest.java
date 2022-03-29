package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.valueObject.Description;
import switch2021.project.model.Typology.Typology;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TypologyStoreTest {

    @DisplayName("Test typology Store populated and empty store - ")
    @Test
    public void typologyStoreTest2() {
        //Arrange
        TypologyStore test = new TypologyStore();
        TypologyStore test2 = new TypologyStore();
        test2.populateDefault();
        //Assert
        assertEquals( 0, test.getTypologyList().size());
        assertNotEquals(test, test2);
    }

    @DisplayName("Test Populate Default")
    @Test
    public void populateTypologyList() {
        //Arrange
        TypologyStore test = new TypologyStore();
        TypologyStore test2 = new TypologyStore();
        Typology tes1 = new Typology("Fixed Cost");
        Typology tes2 = new Typology("Time and Materials");
        test2.saveTypology(tes1);
        test2.saveTypology(tes2);
        //Act
        test.populateDefault();
        //Assert
        assertEquals( 2,test.getTypologyList().size());
        assertEquals(test.getTypologyList(), test2.getTypologyList());
        assertEquals(tes1, test.getTypologyByDescription("Fixed Cost"));
        assertEquals(tes2, test.getTypologyByDescription("Time and Materials"));
    }

    /**
     * Este método faz teste do construtor de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology()
     **/

    @DisplayName("Test save Typology")
    @Test //Test for adding new typology not null at Typology List checking attributes and list size.
    public void saveTypologyTestNotNull() {
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
        Typology typo2 = mock(Typology.class);
        Description desc = mock(Description.class);
        when(typo2.getDescription()).thenReturn(desc);
        when(desc.getText()).thenReturn("Test");
//        Typology typo2 = new Typology("Test");
        //Act
        typologyStore.saveTypology(typo2);
        //Assert
        assertEquals("Test", typo2.getDescription().getText());
        assertEquals(3, typologyStore.getTypologyList().size());
    }

    @DisplayName("Add and save several typologies at same time")
    @Test
    public void addMultiplesTypologiesAtSameTime() {
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
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

//        Description description = typo3.getDescription();
//        Description value_description = mock(Description.class);
//        when(value_description.getText()).thenReturn("TestTypology3");
//        Description value_description = new Description("TestTypology3");

        //Assert
//        assertEquals(description, value_description);
        assertEquals(5,typologyStore.getTypologyList().size());
    }

    @Test
    public void getTypologyWithDescriptionTest(){
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
        // Act
        Typology descriptionTest = typologyStore.getTypologyByDescription("Time and Materials");
        //Assert
        assertEquals(descriptionTest, new Typology("Time and Materials"));
    }

    @Test
    public void getTypologyWithWrongDescriptionTest(){
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
        // Act
        Typology descriptionTest = typologyStore.getTypologyByDescription("time and materials");
        //Assert
        assertNull(descriptionTest);
    }

    @Test
    public void getTypologyListTest() {
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
        TypologyStore test = new TypologyStore();
        //Act
        test.populateDefault();
        //Assert
        assertEquals(typologyStore.getTypologyList(), test.getTypologyList());
    }

    @Test
    public void validateTypology(){
        //Arrange
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
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
        TypologyStore typologyStore = new TypologyStore();
        typologyStore.populateDefault();
        Typology typo = new Typology("Test");
        //Act
        typologyStore.saveTypology(typo);
        int size = typologyStore.getTypologyList().size();
        //Assert
        assertEquals(3,size);
    }

    @Test
    public void thisExistTest() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            TypologyStore typologyStore = new TypologyStore();
            typologyStore.populateDefault();
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

    @DisplayName("Create Typology Fail")
    @Test
    public void createTypoFail(){
        assertThrows(IllegalArgumentException.class, () -> {

            TypologyStore typo = new TypologyStore();
        typo.populateDefault();
        boolean result = typo.createTypology("Fixed Cost");
        assertFalse(result);
        });
    }

    @DisplayName("Create Typology False")
    @Test
    public void createTypoFalse(){
            TypologyStore typo2 = mock(TypologyStore.class);
            when(typo2.createTypology("Te")).thenReturn(false);
            assertFalse(typo2.createTypology("Te"));
    }

//    @Test
//    public void shouldNotCreateAndAddRepeatedTitleProject() throws Exception
//    {
//        // Arrange
//        String titulo = "Título";
//
//        Typology projectDouble = mock( Typology.class );
//        Description desc = mock(Description.class);
//        when( projectDouble.getDescription()).thenReturn( desc );
//        when( desc.getText()).thenReturn( titulo );
//
//
//        TypologyStore projectFactoryDouble =  mock( TypologyStore.class );
//        when(projectFactoryDouble.createTypology(titulo) ).thenReturn( true );
//
//        TypologyStore storeProjectReeng = new TypologyStore();
//
//        // should work fine
//        boolean hasCreated = storeProjectReeng.createTypology( titulo);
//
//        // Act + Assert
//        // throws IllegalArgumentException, because repeated title
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            storeProjectReeng.createTypology( titulo);
//        });
//
//        String expectedMessage = "Repeated typology description inserted.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }



    @DisplayName("Create Typology True")
    @Test
    public void createTypoTrue(){
        TypologyStore typo = new TypologyStore();
        typo.populateDefault();
        boolean result = typo.createTypology("Teste");
        assertTrue(result);
    }

    @DisplayName("Override")
    @Test
    public void OverrideEquals(){
        TypologyStore typo = new TypologyStore();
        typo.populateDefault();
        TypologyStore typo2 = new TypologyStore();
        typo2.populateDefault();

        assertEquals(typo, typo2);
        assertEquals(typo, typo);
    }

    @DisplayName("Override not Equal")
    @Test
    public void OverrideNotEquals(){
        TypologyStore typo = new TypologyStore();
        typo.populateDefault();
        Typology typo2 = mock(Typology.class);

        assertNotEquals(typo, typo2);
    }
}
