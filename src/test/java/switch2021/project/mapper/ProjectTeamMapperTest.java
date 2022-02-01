package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ResourceDto;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTeamMapperTest {

    @Test
    @DisplayName("Project Team Mapper - toDto")
    public void ProjectMappertoDto() {
        //Arrange
        Company company = new Company();

        //create project and save it
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        ProjectRole projectRole = company.getProjectRoleStore().getProjectRole("Team Member");

        //Create resource
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        //Create resource
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user3, startDateMj, endDateMj, 100, .5);
        //Create resource
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user5, startDateMf, endDateMf, 100, 1);

        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        //Resource List
        List<Resource> projTeam = proj1.getProjectTeam().getProjectTeamList();

        //create ResourceDto
        ResourceDto resourceDtoExp1 = new ResourceDto("manuelbras", 2021,11,1,2022,11,15,100,0.5);
        ResourceDto resourceDtoExp3 = new ResourceDto("manueljose", 2021,11,1,2022,11,15,100,.5);
        ResourceDto resourceDtoExp5 = new ResourceDto("manuelfernandes", 2021,11,16,2022,11,30,100,1);
        //create ResourceDtoList
        List<ResourceDto> resourceDtoListExp = new ArrayList<>();
        resourceDtoListExp.add(resourceDtoExp1);
        resourceDtoListExp.add(resourceDtoExp3);
        resourceDtoListExp.add(resourceDtoExp5);

        //Act
        ResourceDto resDto = ProjectTeamMapper.toDto(manuelfernandes);
        List<ResourceDto> resDtoList = ProjectTeamMapper.toDto(projTeam);

        //Asserts
        assertEquals(resourceDtoExp5,resDto);
        assertEquals(resourceDtoListExp,resDtoList);
    }
}
