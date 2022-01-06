package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    @Test
    @DisplayName("Teste de criação de Resource")
    public void associateResource() {
        //Arrange
        /**user **/
        String userName = "xyz";
        String email = "fase";
        String password = "des";
        String function = "gth";
        String photo = "mku";
        SystemUser newUser = new SystemUser(userName, email, password, function, photo);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022,01,05);

        /** project **/
        String code = "opg";
        String projectName = "tre";
        String description = "hjgf";
        String customer = "klf";
        String typology = "wld";
        List<BusinessSector> businessSector = new ProjectSettings().getArrayBusinessSector();
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022,01,05);
        int numberOfSprints = 30;
        int budget = 5000;
        Project newProject = new Project(code, projectName, description, customer, typology,
                businessSector, startDate, numberOfSprints, budget);

        //Resource newResource = new Resource(newUser, startDate, endDate, 100, 50)
        //
        Resource newResource = newProject.createResource(newUser, startDate, endDate, 100, 50);
        newProject.addResource(newResource);
        //Act

        //Assert

    }


}