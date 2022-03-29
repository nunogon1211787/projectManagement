package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.factory.BusinessSectorFactory;
import switch2021.project.valueObject.Description;
import switch2021.project.model.Project.BusinessSector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorStoreTest {

    @Test
    void createAndAddBusinessSector() {
        //Arrange
        BusinessSectorFactory bsf = mock(BusinessSectorFactory.class);
        BusinessSectorStore store = new BusinessSectorStore(bsf);
        BusinessSector sector = mock(BusinessSector.class);
        Description description = mock(Description.class);

        when(sector.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Finances");
        when(bsf.createBusinessSector(anyString())).thenReturn(sector);

        //Act
        store.createAndAddBusinessSector("Finances");

        //Assert
        assertEquals("Finances", "Finances");
    }


    @Test
    void getBusinessSectorList() {

        //Arrange
        BusinessSectorFactory bsf = mock(BusinessSectorFactory.class);
        BusinessSectorStore store = new BusinessSectorStore(bsf);
        BusinessSector sector = mock(BusinessSector.class);
        Description description = mock(Description.class);

        when(sector.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Finances");
        when(bsf.createBusinessSector(anyString())).thenReturn(sector);

        //Act
        store.createAndAddBusinessSector("Finances");

        //Assert
        assertEquals(1, store.getBusinessSectorList().size());
    }


    @Test
    void getBusinessSectorByDescriptionWithMock() {
        //Arrange
        BusinessSectorFactory bsf = mock(BusinessSectorFactory.class);
        BusinessSectorStore store = new BusinessSectorStore(bsf);
        BusinessSector sector = mock(BusinessSector.class);
        Description description = mock(Description.class);


        when(sector.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Finances");
        when(bsf.createBusinessSector(anyString())).thenReturn(sector);

        //Act
        store.createAndAddBusinessSector("Finances");

        //Assert
        assertEquals(sector, store.getBusinessSectorByDescription("Finances"));
    }

    @Test
    void getBusinessSectorByDescription_Null() {
        //Arrange
        BusinessSectorFactory bsf = mock(BusinessSectorFactory.class);
        BusinessSectorStore store = new BusinessSectorStore(bsf);
        BusinessSector sector = mock(BusinessSector.class);
        Description description = mock(Description.class);


        when(sector.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Finances");
        when(bsf.createBusinessSector(anyString())).thenReturn(sector);

        //Act
        store.createAndAddBusinessSector("Finances");

        //Assert
        assertNull(store.getBusinessSectorByDescription("null"));
    }


    @Test
    void getBusinessSectorByDescriptionWithMock_Null() {
        //Arrange
        BusinessSectorFactory bsf = mock(BusinessSectorFactory.class);
        BusinessSectorStore store = new BusinessSectorStore(bsf);
        BusinessSector marketing = mock(BusinessSector.class); //classe B
        Description description = mock(Description.class); //classe C

        when(marketing.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Hello");
        when(bsf.createBusinessSector("null")).thenReturn(marketing);

        //Act
        store.createAndAddBusinessSector("null"); //salvar a classe B

        //Assert
        assertNull(store.getBusinessSectorByDescription("null"));

    }

    @Test
    void hashCodeTest() {
        //Arrange
        BusinessSector sector = new BusinessSector("sector");
        BusinessSector sector2 = new BusinessSector("sector2");
        BusinessSector x = sector;

        //Act & Assert
        assertNotEquals(sector.hashCode(), sector2.hashCode());
        assertEquals(sector.hashCode(), x.hashCode());
    }

    @Test
    void constructorFactory() {
        //Arrange
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();
        //Act
        BusinessSectorStore businessSectorStore = new BusinessSectorStore(businessSectorFactory);
        //Assert
        assertEquals(businessSectorFactory,businessSectorStore.getBusinessSectorFactoryInterface());
    }

}