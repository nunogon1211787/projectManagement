package switch2021.project.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class)
//public class UserStoryFactoryTest {
//
//    @InjectMocks
//    UserStoryFactory userStoryFactory;
//
//    @Mock
//    UserStory newUserStory;
//    @Mock
//    VOFactoryInterface<UserStoryID> usIDFactory;
//    @Mock
//    IValueObjectsFactory<UsPriority> priorityFactory;
//    @Mock
//    UsPriority priority;
//    @Mock
//    IValueObjectsFactory<ProjectID> projectIDFactory;
//    @Mock
//    ProjectID projectID;
//    @Mock
//    IValueObjectsFactory<UsTitle> usTitleFactory;
//    @Mock
//    UsTitle usTitle;
//    @Mock
//    IValueObjectsFactory<Description> descriptionFactory;
//    @Mock
//    Description description;
//    @Mock
//    IValueObjectsFactory<UsHour> timeEstimateFactory;
//    @Mock
//    UsHour timeEstimate;
//    @Mock
//    UserStoryDTO userStoryDTO;
//    @Mock
//    UserStoryID userStoryID;
//
////    @BeforeEach
////    public void setUp() throws Exception {
////        MockitoAnnotations.openMocks(this);
////    }
//
//
//    @Test
//    @DisplayName("createUserStory - with success")
//    void itShouldCreateASystemUser() {
//        //Arrange
//        userStoryDTO.projectID="Project_2022_1";
//        userStoryDTO.title="As a PO, i want to test this string";
//        userStoryDTO.description="make some tests";
//        userStoryDTO.timeEstimate= 5.0;
//        userStoryDTO.priority=1;
//
////        when(userStoryDTO.projectID).thenReturn("Project_2022_1");
////        when(userStoryDTO.title).thenReturn("As a PO, i want to test this string");
////        when(userStoryDTO.description).thenReturn("make some tests");
////        when(userStoryDTO.timeEstimate).thenReturn(5.0);
////        when(userStoryDTO.priority).thenReturn(1);
//
//        when(projectIDFactory.create(userStoryDTO.projectID)).thenReturn(projectID);
//        when(usTitleFactory.create(userStoryDTO.title)).thenReturn(usTitle);
//        when(descriptionFactory.create(userStoryDTO.description)).thenReturn(description);
//        when(timeEstimateFactory.create(userStoryDTO.timeEstimate)).thenReturn(timeEstimate);
//        when(priorityFactory.create(userStoryDTO.priority)).thenReturn(priority);
//        when(usIDFactory.create(userStoryDTO.projectID, userStoryDTO.title)).thenReturn(userStoryID);
//
//        //Act
//        newUserStory = userStoryFactory.createUserStory(userStoryDTO);
//        //Assert
//        assertNotNull(userStoryFactory);
//        assertEquals(userStoryDTO.title, newUserStory.getUserStoryID().getUsTitle().getTitleUs());
//
//    }

