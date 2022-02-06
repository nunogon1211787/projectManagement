
package switch2021.project.mapper;

import switch2021.project.dto.ResourceDTO;
import switch2021.project.model.Resource;
import java.util.ArrayList;
import java.util.List;

public class ProjectTeamMapper {

    /**
     * Method to change data in to a Resource DTO
     **/

    public ResourceDTO toDto(Resource resource){
        String startDate = resource.getStartDate().getYear()
                + "/" + resource.getStartDate().getMonthValue() + "/" + resource.getStartDate().getDayOfMonth();
        String endDate = resource.getEndDate().getYear()
                + "/" + resource.getEndDate().getMonthValue() + "/" + resource.getEndDate().getDayOfMonth();
        String role = null;
        if(resource.getRole() != null){
            role = resource.getRole().getName();
        }
        return new ResourceDTO(resource.getUser().getUserName(),
                role, startDate, endDate, resource.getCostPerHour(), resource.getPercentageOfAllocation());
    }

    /**
     * Method to change data in to a Resource DTO List
     **/

    public List<ResourceDTO> toDto(List<Resource> resourceList){
        List<ResourceDTO> resourceDTOList = new ArrayList<>();

        for(Resource resource: resourceList){
            ResourceDTO resourceDto = toDto(resource);
            resourceDTOList.add(resourceDto);
        }
        return resourceDTOList;
    }
}
