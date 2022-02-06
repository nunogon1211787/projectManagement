package switch2021.project.mapper;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectsMapperTest {

    @Test
    public void projectMapperToDTOTest() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj1);
        //Project 2
        Project proj2 = company.getProjectStore().createProject("prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj2);
        //Project 3
        Project proj3 = company.getProjectStore().createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj3);
        //Project 4
        Project proj4 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj4);
        //Act
        projectsMapper.toDTO(company.getProjectStore().getProjectList());

        List<ProjectDTO> test = new ArrayList<>();
        ProjectDTO projectDTO1 = new ProjectDTO("Project_2022_1", "prototype1", "2021/11/1", null);
        test.add(projectDTO1);
        ProjectDTO projectDTO2 = new ProjectDTO("Project_2022_2", "prototype2", "2021/11/1", null);
        test.add(projectDTO2);
        ProjectDTO projectDTO3 = new ProjectDTO("Project_2022_3", "prototype3", "2021/11/1", null);
        test.add(projectDTO3);
        ProjectDTO projectDTO4 = new ProjectDTO(null, null, null, null);
        test.add(projectDTO4);
        //Assert
        assertEquals(test.size(), projectsMapper.getProjectDTOList().size());
        assertNull(projectDTO4.getCode());
        assertNull(projectDTO4.getProjectName());
        assertNull(projectDTO4.getStartDate());
        assertNull(projectDTO4.getEndDate());
    }

    @Test
    public void ProjectMapperDTOEndDateNullTest() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj1);
        //Act
        ProjectDTO test = projectsMapper.toDTO(proj1);
        //Assert
        assertEquals("Project_2022_1", test.getCode());
        assertEquals("prototype1", test.getProjectName());
        assertEquals("2021/11/1", test.getStartDate());
        assertNull(test.getEndDate());
    }

    @Test
    public void ProjectMapperDTOEndDateNotNullTestSuccess() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 1));
        company.getProjectStore().saveNewProject(proj1);
        //Act
        ProjectDTO test = projectsMapper.toDTO(proj1);
        //Assert
        assertEquals("Project_2022_1", test.getCode());
        assertEquals("prototype1", test.getProjectName());
        assertEquals("2021/11/1", test.getStartDate());
        assertEquals("2022/11/1", test.getEndDate());
    }

    @Test
    public void ProjectMapperDTOEndDateNotNullTestFail() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 1));
        company.getProjectStore().saveNewProject(proj1);
        //Act
        ProjectDTO test = projectsMapper.toDTO(proj1);
        //Assert
        assertNotEquals("Project2022_1", test.getCode());
        assertNotEquals("type1", test.getProjectName());
        assertNotEquals("2021/11/2", test.getStartDate());
        assertNotEquals("2022/11/2", test.getEndDate());
    }

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
    public void overrideTest() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        //Act
        List<ProjectDTO> toDTO = projectsMapper.toDTO(company.getProjectStore().getProjectList());
        List<ProjectDTO> test = projectsMapper.getProjectDTOList();
        List<ProjectDTO> test1 = new ArrayList<>();
        test1.add(projectsMapper.toDTO(proj1));
        //Assert
        assertNotSame(test1, toDTO);
        assertEquals(test, toDTO);
        assertEquals(test.hashCode(), toDTO.hashCode());
        assertNotEquals(test, test1);
        assertNotEquals(test.hashCode(), test1.hashCode());
    }


    @Test
    public void overrideHashCodeNotEqualTest() {
        //Arrange and Act
        ProjectDTO projectDTO1 = new ProjectDTO("Project_2022_1", "prototype1", "2021/11/1", null);
        ProjectDTO projectDTO3 = new ProjectDTO("Project_2022_3", "prototype3", "2022/11/1", null);
        //Assert
        assertNotSame(projectDTO1, projectDTO3);
        assertNotEquals(projectDTO1, projectDTO3);
        assertNotEquals(projectDTO1.hashCode(), projectDTO3.hashCode());
        assertNotEquals(projectDTO1.getCode(), projectDTO3.getCode());
        assertNotEquals(projectDTO1.getProjectName(), projectDTO3.getProjectName());
        assertNotEquals(projectDTO1.getStartDate(), projectDTO3.getStartDate());
    }
}
