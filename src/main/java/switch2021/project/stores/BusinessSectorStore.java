package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.BusinessSector;
import switch2021.project.model.ProjectTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

public class BusinessSectorStore {

    /**
     * Business Sector Store Attributes (Contains a Business Sector list)
     **/

    private final List<BusinessSector> businessSectorList;

    /**
     * Business Sector Constructor
     **/

    public BusinessSectorStore() {
        this.businessSectorList = new ArrayList<>();
    }

    /**
     * Add Business Sector Method (Creates a new Business Sector object of the customer)
     **/

    public BusinessSector createBusinessSector(String description) {
        return new BusinessSector(description);
    }

    /**
     * Add Project Status Method (Adds a new Project Status object to the Project Status List)
     **/

    public boolean addBusinessSector(BusinessSector sector) {
        this.businessSectorList.add(sector);
        return true;
    }

    /**
     * Getter Methods
     **/

    public List<BusinessSector> getBusinessSectorList() {
        return new ArrayList<>(businessSectorList);
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

    /**
     * Override
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSectorStore)) return false;
        BusinessSectorStore that = (BusinessSectorStore) o;
        return Objects.equals(this.businessSectorList, that.businessSectorList);
    }

    /**
     * Hash
     **/

    @Override
    public int hashCode() {
        return Objects.hash(businessSectorList);
    }

}
