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

        assertNull( store.getBusinessSectorByDescription("null"));
    }
}