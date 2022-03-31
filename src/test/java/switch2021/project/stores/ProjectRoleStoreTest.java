package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.factory.ProjectRoleFactory;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectRole;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectRoleStoreTest {



    @Test
    public void projectRoleStoreConstructorTest() {
        //Arrange
        ProjectRoleFactory projectRoleFactory = new ProjectRoleFactory();
        //Act
        ProjectRoleStore projectRoleStore = new ProjectRoleStore(projectRoleFactory);
        //Assert
        assertEquals(projectRoleFactory, projectRoleStore.getProjectRoleFactory());
    }

    @Test
    public void shouldCreateAndAddProject(){
        // Arrange
        String titulo = "Título";

        ProjectRole projectRoleDouble = mock( ProjectRole.class );
        ProjectRoleFactory projectRoleFactoryDouble =  mock( ProjectRoleFactory.class );
        when(projectRoleFactoryDouble.createProjectRole(anyString()) ).thenReturn( projectRoleDouble );

        ProjectRoleStore sp = new ProjectRoleStore( projectRoleFactoryDouble );
        boolean hasCreated = sp.createAndAddProjectRole( titulo);

        assertTrue( hasCreated );
    }

    @Test
    public void shouldCreateAndAddExistentProject() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            String titulo = "Título";

            ProjectRole projectRoleDouble = mock(ProjectRole.class);
            ProjectRoleFactory projectRoleFactoryDouble = mock(ProjectRoleFactory.class);
            Description name = mock(Description.class);
            when(projectRoleFactoryDouble.createProjectRole(anyString())).thenReturn(projectRoleDouble);
            when(projectRoleDouble.getName()).thenReturn(name);
            when(name.getText()).thenReturn("Título");

            ProjectRoleStore sp = new ProjectRoleStore(projectRoleFactoryDouble);
            sp.createAndAddProjectRole(titulo);
            sp.createAndAddProjectRole(titulo);

        });
    }

    @Test
    public void shouldCreateAndAddExistentProject3() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            String titulo = "Título";

            ProjectRoleFactory projectRoleFactory = new ProjectRoleFactory();

            ProjectRoleStore store = new ProjectRoleStore(projectRoleFactory);
            store.createAndAddProjectRole(titulo);
            store.createAndAddProjectRole(titulo);

        });
    }
//
//    @Test
//    public void createProjectRoleTestSuccess() {
//        //Arrange
//        Company company = new Company();
//        //Act
//        ProjectRole tester = company.getProjectRoleStore().createProjectRole("Tester");
//        company.getProjectRoleStore().saveProjectRole(tester);
//        //Assert
//        assertEquals(tester, company.getProjectRoleStore().getProjectRole("Tester"));
//    }
//
//    @Test
//    public void createProjectRoleTestNullFail() {
//        //Assert
//        assertThrows(NullPointerException.class, () -> {
//            //Arrange
//            Company company = new Company();
//            //Act
//            company.getProjectRoleStore().createProjectRole(null);
//        });
//    }
//
//    @Test
//    public void createProjectRoleTestFail() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            Company company = new Company();
//            //Act
//            company.getProjectRoleStore().createProjectRole("");
//        });
//    }
//
//    @Test
//    public void validateProjectRoleExistTestSuccess() {
//        //Arrange
//        Company company = new Company();
//        //Act
//        ProjectRole tester = new ProjectRole("Tester");
//        // Assert
//        assertTrue(company.getProjectRoleStore().saveProjectRole(tester));
//    }
//
//    @Test
//    public void validateProjectRoleExistTestFail() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            Company company = new Company();
//            //Act
//            ProjectRole tester = new ProjectRole("Tester");
//            company.getProjectRoleStore().saveProjectRole(company.getProjectRoleStore().getProjectRole("Team Member"));
//        });
//    }
//
//    @Test
//    public void overrideAndHashCodeTest() {
//        //Arrange
//        ProjectRoleStore list1 = new ProjectRoleStore();
//        list1.saveProjectRole(list1.createProjectRole("new"));
//        ProjectRoleStore list2 = new ProjectRoleStore();
//        list2.saveProjectRole(list1.createProjectRole("new"));
//        ProjectRoleStore list3 = new ProjectRoleStore();
//        list3.saveProjectRole(list3.createProjectRole("not new"));
//        //Assert
//        assertNotSame(list1, list2);
//        assertEquals(list1, list2);
//        assertEquals(list1.hashCode(), list2.hashCode());
//        assertNotEquals(list1, list3);
//        assertNotEquals(list1.hashCode(), list3.hashCode());
//    }
}