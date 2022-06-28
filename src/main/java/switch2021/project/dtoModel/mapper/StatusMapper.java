package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputStatusDTO;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusMapper {

    public OutputStatusDTO toDTO(String status) {

        return new OutputStatusDTO(status);
    }

    public CollectionModel<OutputStatusDTO> toCollectionDto(List<ProjectStatusEnum> status) {
        List<OutputStatusDTO> projectStatusEnum = new ArrayList<>();

        for (ProjectStatusEnum status_i : status) {
            projectStatusEnum.add(new OutputStatusDTO(status_i.toString()));
        }

        return CollectionModel.of(projectStatusEnum);
    }
}
