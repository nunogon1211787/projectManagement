package switch2021.project.stores;

import switch2021.project.model.BusinessSector;

import java.util.ArrayList;
import java.util.List;

public class BusinessSectorStore {

    /**
     * Business Sector Store Atributes
     * Contains a Business Sector list
     **/

    private final List<BusinessSector> businessSectorList;

    /**
     * Business Sector Constructor
     **/

    public BusinessSectorStore() {
        this.businessSectorList = new ArrayList<>();
    }

    /**
     * Add Business Sector Method
     * Creates a new Business Sector object of the customer
     **/

    public BusinessSector createBusinessSector(String description) {
        return new BusinessSector(description);
    }

    /**
     * Add Project Status Method
     * Adds a new Project Status object to the Project Status List
     **/

    public boolean addBusinessSector(BusinessSector sector) {
        this.businessSectorList.add(sector);
        return true;
    }

    /**
     * Métodos Getter e Setter
     **/

    public List<BusinessSector> getBusinessSectorList() {

        return this.businessSectorList;
    }

    public BusinessSector getBusinessSectorByDescription(String description) {
        BusinessSector sector = null;
        for (BusinessSector i : this.businessSectorList)
            if (i.getDescription().equals(description)) {
                sector = i;
                break;
            }

        return sector;
    }
}
