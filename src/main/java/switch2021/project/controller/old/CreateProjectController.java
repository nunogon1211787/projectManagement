//package switch2021.project.controller.old;
//
//import switch2021.project.model.*;
//import switch2021.project.model.Project.Project;
//import switch2021.project.model.valueObject.BusinessSector;
//import switch2021.project.model.valueObject.Customer;
//import switch2021.project.model.Typology.Typology;
//
//import java.time.LocalDate;
//
//public class CreateProjectController {
//
//    /**
//     * Attributes
//     **/
//    private final Company company;
//    private Typology typology;
//    private Customer customer;
//    private BusinessSector sector;
//
//
//    /**
//     * Constructor to test (without SINGLETON)
//     **/
//    public CreateProjectController(Company company) {
//        this.company = company;
//    }
//
//
//    /**
//     * Methods
//     **/
//    public Typology getTypology(String typo) {
//        this.typology = company.getTypologyRepository().findTypologyById(typo);
//        return this.typology;
//    }
//
//    public void getCustomer(String customer) {
//        this.customer = company.getCustomerStore().getCustomerByName(customer);
//    }
//
//    public void getBusinessSector(String sector) {
//        this.sector = company.getBusinessSectorStore().getBusinessSectorByDescription(sector);
//    }
//
//    public boolean createProject(String name, String description, LocalDate startDate, int numberOfSprints, int budget) {
//        Project project = this.company.getProjectStore().createAndSaveProject(name, description, customer,
//                typology, sector, startDate, numberOfSprints, budget);
//
//        return project != null;
//    }
//}
