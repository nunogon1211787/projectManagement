package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.model.BusinessSector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorStoreTest {

    @Test
    void createBusinessSector() {
        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();

        //Act
        store.addBusinessSector(store.createBusinessSector("teste"));

        //Assert
        assertEquals("teste", store.getBusinessSectorList().get(0).getDescription().getText());
    }

    @Test
    void addBusinessSector() {

        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");

        //Act
        store.addBusinessSector(sector);

        //Assert
        assertEquals(1, store.getBusinessSectorList().size());
        assertEquals(sector, store.getBusinessSectorList().get(0));
    }

    @Test
    void getBusinessSectorList() {
        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");
        BusinessSector sector2 = new BusinessSector("teste2");

        //Act
        store.addBusinessSector(sector);
        store.addBusinessSector(sector2);
        List<BusinessSector> sectorList = new ArrayList<>();
        sectorList.add(sector);
        sectorList.add(sector2);

        //Assert
        assertEquals(sectorList, store.getBusinessSectorList());
    }

    @Test
    void getBusinessSectorByDescription() {
        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");

        //Act
        store.addBusinessSector(sector);

        //Assert
        assertEquals("teste", sector.getDescription().getText());
    }

    @Test
    void getBusinessSectorByDescription_Null() {
        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");

        //Act
        store.addBusinessSector(sector);

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
        assertNotEquals(sector.hashCode(),sector2.hashCode());
        assertEquals(sector.hashCode(),x.hashCode());
    }

    @Test
    public void setBusinessSectorTest() {
        //Arrange
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");

        //Act
        store.addBusinessSector(sector);
        store.getBusinessSectorList().get(0).setDescription(new Description("testeX"));

        //Assert
        assertEquals("testeX", store.getBusinessSectorList().get(0).getDescription().getText());
    }
}