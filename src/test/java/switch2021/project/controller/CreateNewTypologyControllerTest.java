package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ID_Typology;
import switch2021.project.service.CreateTypologyService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateNewTypologyControllerTest {

    @InjectMocks
    CreateTypologyController controller;
    @Mock
    CreateTypologyService service;
//    @Mock
//    IFactoryTypology iFactoryTypology;
//    @Mock
//    IRepoTypology iRepoTypology;
//    @Mock
//    TypologyMapper typologyMapper;



    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(service.createAndSaveTypology(dto)).thenReturn(dto);
        ResponseEntity<Object> response = controller.createTypology(dto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test //Review test without body, msut have resquest, but the methods without values is passing....
    public void createNewTypologyControllerNullDescriptionTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //Act
        when(service.createAndSaveTypology(null)).thenReturn(null);
        ResponseEntity<Object> response = controller.createTypology(null);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
}
