package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.BusinessSector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorStoreTest {

    @Test
    void createBusinessSector() {
        BusinessSectorStore store = new BusinessSectorStore();
        store.addBusinessSector(store.createBusinessSector("teste"));

        assertEquals("teste", store.getBusinessSectorList().get(0).getDescription());
    }

    @Test
    void addBusinessSector() {
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");
        store.addBusinessSector(sector);

        assertEquals(1, store.getBusinessSectorList().size());
        assertEquals(sector, store.getBusinessSectorList().get(0));
    }

    @Test
    void getBusinessSectorList() {
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");
        BusinessSector sector2 = new BusinessSector("teste2");
        store.addBusinessSector(sector);
        store.addBusinessSector(sector2);

        List<BusinessSector> sectorList = new ArrayList<>();
        sectorList.add(sector);
        sectorList.add(sector2);

        assertEquals(sectorList, store.getBusinessSectorList());
    }

    @Test
    void getBusinessSectorByDescription() {
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");
        store.addBusinessSector(sector);

        assertEquals(sector, store.getBusinessSectorByDescription("teste"));
    }

    @Test
    void getBusinessSectorByDescription_Null() {
        BusinessSectorStore store = new BusinessSectorStore();
        BusinessSector sector = new BusinessSector("teste");
        store.addBusinessSector(sector);

        assertNull(store.getBusinessSectorByDescription("null"));
    }

    @Test
    void hashCodeTest() {
        BusinessSector sector = new BusinessSector("sector");
        BusinessSector sector2 = new BusinessSector("sector2");
        BusinessSector x = sector;

        assertNotEquals(sector.hashCode(),sector2.hashCode());
        assertEquals(sector.hashCode(),x.hashCode());
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        BusinessSectorStore list1 = new BusinessSectorStore();
        list1.addBusinessSector(list1.createBusinessSector("new"));
        BusinessSectorStore list2 = new BusinessSectorStore();
        list2.addBusinessSector(list1.createBusinessSector("new"));
        BusinessSectorStore list3 = new BusinessSectorStore();
        list3.addBusinessSector(list3.createBusinessSector("not new"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}