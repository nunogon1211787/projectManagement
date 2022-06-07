package switch2021.project.dtoModel.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.interfaceAdapters.controller.ResourceController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceMapper {

    public OutputResourceDTO model2Dto(Resource res) {

        OutputResourceDTO resDto = new OutputResourceDTO();

        resDto.user = res.getId().getUser().getEmail().getEmailText();
        resDto.project = res.getId().getProject().getCode();
        resDto.role = res.getRole().toString();
        resDto.startDate = res.getId().getStartDate().toString();
        resDto.endDate = res.getEndDate().toString();
        resDto.allocation = res.getAllocation().toString();
        resDto.cost = res.getCost().toString();

////        //Show a resource
////        resDto.add(linkTo(methodOn(ResourceController.class).showCurrentProjectTeam())
////                .withSelfRel());
//
//        //Show all Resources in the project
//        resDto.add(linkTo(methodOn(ResourceController.class).showCurrentProjectTeam(resDto.project).withRel("Collection"));
//
        //Delete

//        resDto.add(linkTo(methodOn(ResourceController.class).
//        ).withRel("Delete"));

//        //Edit a project
//
//        projDto.add(linkTo(methodOn(ProjectController.class).updateProjectPartially(projDto.code,
//                new EditProjectInfoDTO())).withRel("Edit"));

        return resDto;
    }
}
