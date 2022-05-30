//package switch2021.project.entities.factories.factory;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import switch2021.project.dtoModel.dto.ProjectDTO;
//import switch2021.project.entities.factories.factoryInterface.*;
//import switch2021.project.model.Project.ProjectReeng;
//import switch2021.project.model.Project.ProjectStatusEnum;
//import switch2021.project.model.valueObject.*;
//
//import java.time.LocalDate;
//
//import static java.lang.Integer.parseInt;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//public class ProjectFactoryTest {
//
//    @Mock
//    private IDescriptionFactory iDescription;
//    @Mock
//  private  IDescriptionFactory iName;
//    @Mock
//  private  IBusinessSectorFactory iBusinessSector;
//    @Mock
//   private INumberOfSprintsFactory iNumberOfSprints;
//    @Mock
//  private  ISprintDurationFactory iSprintDuration;
//    @Mock
//   private IBudgetFactory iBudget;
//    @Mock
//  private   Description description;
//    @Mock
// private    Description name;
//    @Mock
//    private  BusinessSector businessSector;
//    @Mock
//    private  NumberOfSprints numberOfSprints;
//    @Mock
//    private  SprintDuration sprintDuration;
//    @Mock
//    private  Budget budget;
//    @Mock
//    private   ProjectDTO projectDTO;
//    @Mock
//   private ProjectID projectID;
//
//    @InjectMocks
//    ProjectFactory projectFactory;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("createProject(ProjectDTO projectDTO, int nextId)")
//    void createProject() {
//        //Arrange
//        when(iName.createDescription(projectDTO.projectName)).thenReturn(name);
//        when(iDescription.createDescription(projectDTO.description)).thenReturn(description);
//        when(iBusinessSector.createBusinessSector(projectDTO.businessSector)).thenReturn(businessSector);
//        when(iNumberOfSprints.create(Integer.parseInt((projectDTO.numberOfSprints)))).thenReturn(numberOfSprints);
//        when(iSprintDuration.create(parseInt(projectDTO.sprintDuration))).thenReturn(sprintDuration);
//        when(iBudget.create(parseInt(projectDTO.budget))).thenReturn(budget);
//        when(LocalDate.parse(projectDTO.startDate)).thenReturn(LocalDate.now());
//        //Act
//        ProjectReeng newProject = projectFactory.createProject(projectDTO, 1);
//        newProject.setProjectCode(projectID);
//        newProject.setProjectStatus(ProjectStatusEnum.PLANNED);
//        //Assert
//        assertNotNull(newProject);
//    }
//}
//
