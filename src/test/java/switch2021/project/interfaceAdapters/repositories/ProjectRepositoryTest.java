package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dataModel.JPA.assembler.ProjectJpaAssembler;
import switch2021.project.persistence.ProjectJpaRepository;

import static org.mockito.Mockito.mock;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProjectRepositoryTest {

    @MockBean
    ProjectJpaRepository projectJpaRepository;

    @MockBean
    ProjectJpaAssembler projectJpaAssembler;

    @InjectMocks
    ProjectRepository projRepo;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    //


}
