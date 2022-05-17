package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.factoryInterface.IBusinessSectorFactory;
import switch2021.project.model.valueObject.BusinessSector;

import java.util.ArrayList;
import java.util.List;

@Getter

public class BusinessSectorStore {

    /**
     * Business Sector Store Attributes (Contains a Business Sector list)
     **/
    private final List<BusinessSector> businessSectorList;
    private final IBusinessSectorFactory IBusinessSectorFactory;


    /**
     * Business Sector Constructor
     **/
    public BusinessSectorStore(IBusinessSectorFactory businessSectorFI) {
        this.businessSectorList = new ArrayList<>();
        this.IBusinessSectorFactory = businessSectorFI;
    }


    /**
     * Add Business Sector Method (Creates a new Business Sector object of the customer)
     * Adds a new Project Status object to the Project Status List)
     **/
    public boolean createAndAddBusinessSector(String description) {

        if (getBusinessSectorByDescription(description) != null) {
            return false;
        } else {
            this.businessSectorList.add(IBusinessSectorFactory.createBusinessSector(description));
        }
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
            if (i.getDescription().getText().equals(description)) {
                sector = i;
                break;
            }
        return sector;
    }
}
