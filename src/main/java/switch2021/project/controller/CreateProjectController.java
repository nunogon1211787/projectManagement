package switch2021.project.controller;

import switch2021.project.model.*;

import java.time.LocalDate;

public class CreateProjectController {
    private Company company;
    private Project project;

    public CreateProjectController(Company company) {
        this.company = company;
        this.project = null;
    }

    public Typology getTypology(String typo) {
        return company.getTypologyStore().getTypology(typo);
    }

    public Customer getCustomer(String customer) {
        return company.getCustomerStore().getCustomerByName(customer);
    }

    public BusinessSector getBusinessSector(String sector) {
        return company.getBusinessSectorStore().getBusinessSectorByDescription(sector);
    }

    public boolean createProject(String code, String name, String description, String customer, String typology,
                                 String businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        this.project = this.company.getProjectStore().createProject(code, name, description, getCustomer(customer),
                getTypology(typology), getBusinessSector(businessSector), startDate, numberOfSprints, budget);
        return this.company.getProjectStore().saveNewProject(project);
    }

}
