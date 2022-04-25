package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectsMapperTest {


    @Test
    public void ProjectMappertoDTOListNullTest() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        //Act
        List<ProjectDTO> test = new ArrayList<>();
        //Assert
        assertEquals(test, projectsMapper.toDTO(company.getProjectStore().getProjectList()));
    }


    @Test
    @DisplayName("check if the list size is correct")
    public void getProjectListByUserEmailWith2Projects() {
        //Arrange
        Company company = new Company();
        ProjectsMapper mapper = new ProjectsMapper();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createAndSaveProject( "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Project project2 = company.getProjectStore().createAndSaveProject( "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));

        project.addResource(input);
        project2.addResource(input);

        // Act
        List<ProjectDTO> projectList = mapper.toDtoByUser(company.getProjectStore().getProjectList());
        // Assert
        assertEquals(2, projectList.size());

    }

}
