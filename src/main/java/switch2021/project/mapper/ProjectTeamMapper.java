//package switch2021.project.Mapper;
package switch2021.project.mapper;

import switch2021.project.dto.ResourceDto;
import switch2021.project.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ProjectTeamMapper {
    public static ResourceDto toDto(Resource resource){
        return new ResourceDto(resource.getUser().getUserName(), resource.getStartDate().getYear(), resource.getStartDate().getMonthValue(), resource.getStartDate().getDayOfMonth(), resource.getEndDate().getYear(), resource.getEndDate().getMonthValue(), resource.getEndDate().getDayOfMonth(), resource.getCostPerHour(), resource.getPercentageOfAllocation());
    }

    public static List<ResourceDto> toDto(List<Resource> resourceList){
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        for(Resource resource: resourceList){
            ResourceDto resourceDto = new ResourceDto(resource.getUser().getUserName(), resource.getStartDate().getYear(), resource.getStartDate().getMonthValue(), resource.getStartDate().getDayOfMonth(), resource.getEndDate().getYear(), resource.getEndDate().getMonthValue(), resource.getEndDate().getDayOfMonth(), resource.getCostPerHour(), resource.getPercentageOfAllocation());
            resourceDtoList.add(resourceDto);
        }
        return resourceDtoList;
    }
}
