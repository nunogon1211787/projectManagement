package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.GetListHResourceProjectController;
import switch2021.project.dto.old.ResourceDTO;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.mapper.old.ProjectTeamMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.SystemUser.SystemUser;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListHResourceProjectControllerTest {

/*    @Test
    @DisplayName("Get Human Resource List Controller")
    public void GetHResourceProjectController() {
        //Arrange
        Company company = new Company();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
        GetListHResourceProjectController controller = new GetListHResourceProjectController(company, projectTeamMapper);
        //create project and save it
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        ProjectRole projectRole = company.getProjectRoleStore().getProjectRole("Team Member");

        //Create resource
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        manuelbras.setRole(projectRole);
        //Create resource
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user3, startDateMj, endDateMj, 100, .5);
        manueljose.setRole(projectRole);
        //Create resource
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user5, startDateMf, endDateMf, 100, 1);
        manuelfernandes.setRole(projectRole);

        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manuelfernandes);

        //Act
        List<ResourceDTO> resDtoList = controller.getProjectTeam("Project_2022_1");

        //Asserts
        assertEquals("Team Member", resDtoList.get(0).getRole());
        assertEquals("manuelbras", resDtoList.get(0).getUserName());
        assertEquals("2021/11/1", resDtoList.get(0).getStartDate());
        assertEquals("2022/11/15", resDtoList.get(0).getEndDate());
        assertEquals(100, resDtoList.get(0).getCostPerHour());
        assertEquals(.5, resDtoList.get(0).getPercentageOfAllocation());
        assertEquals(3, resDtoList.size());
    }

 */
}