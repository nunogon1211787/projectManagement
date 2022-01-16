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

    public boolean createProject(String code, String name, String description, Customer customer, Typology typology,
                                 BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        this.project = this.company.getProjectStore().createProject(code,name,description, customer,typology,businessSector,
                startDate,numberOfSprints,budget);
        return this.company.getProjectStore().saveNewProject(project);
    }

}
