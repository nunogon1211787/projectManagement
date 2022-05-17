package switch2021.project.mapper;

public class ProjectMapperTest {


//    @Test
//    public void ProjectMappertoDTOListNullTest() {
//        //Arrange
//        Company company = new Company();
//        ProjectsMapper projectsMapper = new ProjectsMapper();
//        //Act
//        List<ProjectDTO> test = new ArrayList<>();
//        //Assert
//        assertEquals(test, projectsMapper.toDTO(company.getProjectStore().getProjectList()));
//    }


/*    @Test
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

 */

}
