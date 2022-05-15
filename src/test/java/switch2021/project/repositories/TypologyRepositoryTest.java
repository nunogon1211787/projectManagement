//package switch2021.project.repositories;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import switch2021.project.model.Typology.Typology;
//import switch2021.project.model.valueObject.Description;
//import switch2021.project.model.valueObject.TypologyID;
//
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
///** >>>>>>>>>>>>>>>>>>REVIEW<<<<<<<<<<<<<<<<<<<< **/
//
//
//public class TypologyRepositoryTest {
//
//    @DisplayName("Test typology Store populated and empty store - ")
//    @Test
//    public void typologySaveSuccess() {
//        //Arrange
//        TypologyRepositoryReeg test = new TypologyRepositoryReeg();
//        Typology typo = mock(Typology.class);
//        TypologyID id = mock(TypologyID.class);
//        Description des = mock(Description.class);
//        //Act
//        when(typo.getId_description()).thenReturn(id);
//        when(id.getDescription()).thenReturn(des);
//        when(des.getText()).thenReturn("Test");
//        //Assert
//        assertTrue(test.saveTypology(typo));
//    }
//
//    @DisplayName("Add and save several typologies at same time")
//    @Test
//    public void typologySaveFail() {
//        //Arrange
//        TypologyRepositoryReeg test = new TypologyRepositoryReeg();
//        Typology typo = mock(Typology.class);
//        Typology typo2 = mock(Typology.class);
//        TypologyID id = mock(TypologyID.class);
//        Description des = mock(Description.class);
//        //Act
//        when(typo.getId_description()).thenReturn(id);
//        when(typo2.getId_description()).thenReturn(id);
//        when(id.getDescription()).thenReturn(des);
//        when(des.getText()).thenReturn("Test");
//        test.saveTypology(typo);
//        when(typo.hasID_Description(des.getText())).thenReturn(true);
//        //Assert
//        assertFalse(test.saveTypology(typo2));
//    }
//
//    @Test
//    public void getTypologyWithDescriptionTest() {
//        //Arrange
//        TypologyRepositoryReeg typologyRepository = new TypologyRepositoryReeg();
//        Typology typo = mock(Typology.class);
//        TypologyID id = mock(TypologyID.class);
//        Description des = mock(Description.class);
//        // Act
//        when(typo.getId_description()).thenReturn(id);
//        when(id.getDescription()).thenReturn(des);
//        when(des.getText()).thenReturn("Time and Materials");
//        when(id.hasDescription("Time and Materials")).thenReturn(true);
//        typologyRepository.saveTypology(typo);
//        //Assert
//        assertNotNull(typologyRepository.findTypologyById("Time and Materials"));
//    }
//
//    @Test
//    public void getTypologyWithDescriptionNullTest() {
//        //Arrange
//        TypologyRepositoryReeg typologyRepository = new TypologyRepositoryReeg();
//        // Act
//        Typology nullTypology = typologyRepository.findTypologyById("Time and Materials");
//        //Assert
//        assertNull(nullTypology);
//    }
//
//    @Test
//    public void getTypologyListTest() {
//        //Arrange
//        TypologyRepositoryReeg typologyRepository = new TypologyRepositoryReeg();
//        Typology typo1 = mock(Typology.class);
//        Typology typo2 = mock(Typology.class);
//        TypologyID id1 = mock(TypologyID.class);
//        TypologyID id2 = mock(TypologyID.class);
//        Description des1 = mock(Description.class);
//        Description des2 = mock(Description.class);
//        //Act
//        when(typo1.getId_description()).thenReturn(id1);
//        when(typo2.getId_description()).thenReturn(id2);
//        when(id1.getDescription()).thenReturn(des1);
//        when(id2.getDescription()).thenReturn(des2);
//        when(des1.getText()).thenReturn("Test1");
//        when(des2.getText()).thenReturn("Test2");
//        typologyRepository.saveTypology(typo1);
//        typologyRepository.saveTypology(typo2);
//        List<Typology> list = typologyRepository.findAllTypology();
//        //Assert
//        assertEquals(2, list.size());
//    }
//
//    @Test
//    public void deleteTypologyTest() {
//        //Arrange
//        TypologyRepositoryReeg typologyRepository = new TypologyRepositoryReeg();
//        Typology typo1 = mock(Typology.class);
//        Typology typo2 = mock(Typology.class);
//        Typology typo3 = mock(Typology.class);
//        TypologyID id1 = mock(TypologyID.class);
//        TypologyID id2 = mock(TypologyID.class);
//        TypologyID id3 = mock(TypologyID.class);
//        Description des1 = mock(Description.class);
//        Description des2 = mock(Description.class);
//        Description des3 = mock(Description.class);
//        when(typo1.getId_description()).thenReturn(id1);
//        when(typo2.getId_description()).thenReturn(id2);
//        when(typo3.getId_description()).thenReturn(id3);
//        when(id1.getDescription()).thenReturn(des1);
//        when(id2.getDescription()).thenReturn(des2);
//        when(id3.getDescription()).thenReturn(des3);
//        when(des1.getText()).thenReturn("Test1");
//        when(des2.getText()).thenReturn("Test2");
//        when(des3.getText()).thenReturn("Test3");
//        typologyRepository.saveTypology(typo1);
//        typologyRepository.saveTypology(typo2);
//        typologyRepository.saveTypology(typo3);
//        //Act
//        when(typo1.hasID_Description("Test3")).thenReturn(false);
//        when(typo2.hasID_Description("Test3")).thenReturn(false);
//        when(typo3.hasID_Description("Test3")).thenReturn(true);
//        typologyRepository.deleteTypology("Test3");
//        //Assert
////        assertEquals(2, typologyRepository.getTypologyList().size());
//    }
//}
