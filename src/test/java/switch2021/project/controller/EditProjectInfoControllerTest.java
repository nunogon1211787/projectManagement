package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class EditProjectInfoControllerTest {


    @Test
    void getProjectList() {
        //Arrange
        Company company = new Company();
        ProjectStore store = company.getProjectStore();
        //Act
        store.saveNewProject(store.createProject("Name", "Description", new Customer("email","Name"),
                company.getTypologyStore().getTypologyByDescription("Fixed Cost"),new BusinessSector("Description"),
                LocalDate.now(),2,10));
        List<Project> proj = store.getProjects();
        //Assert
        assertEquals(proj.size(),1); //Verificar se é válido este Test...
    }

//    @Test
//    void getProjectRequested() {
//    }
//
//    @Test
//    void editProject() {
//    }
//
//    @Test
//    void saveProject() {
//    }



}