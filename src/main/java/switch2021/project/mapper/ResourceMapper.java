package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.model.Resource.ResourceReeng;

@Component
public class ResourceMapper {

    public OutputResourceDTO model2Dto(ResourceReeng res) {

        OutputResourceDTO resDto = new OutputResourceDTO();

        resDto.user = res.getId().getUser().getEmail().getEmail();
        resDto.project = res.getId().getProject().getCode();
        resDto.role = res.getRole().toString();
        resDto.startDate = res.getId().getStartDate().toString();
        resDto.endDate = res.getEndDate().toString();
        resDto.allocation = res.getAllocation().toString();
        resDto.cost = res.getCost().toString();

        return resDto;
    }
}
