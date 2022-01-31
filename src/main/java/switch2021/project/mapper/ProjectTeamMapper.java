
package switch2021.project.mapper;

import switch2021.project.dto.ResourceDto;
import switch2021.project.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ProjectTeamMapper {
    public static ResourceDto toDto(Resource resource){
        String startDate = resource.getStartDate().getYear() + "/" + resource.getStartDate().getMonthValue() + "/" + resource.getStartDate().getDayOfMonth();
        String endDate = resource.getEndDate().getYear() + "/" + resource.getEndDate().getMonthValue() + "/" + resource.getEndDate().getDayOfMonth();
        return new ResourceDto(resource.getUser().getUserName(), resource.getRole().getName(), startDate, endDate, resource.getCostPerHour(), resource.getPercentageOfAllocation());
    }

    public static List<ResourceDto> toDto(List<Resource> resourceList){
        List<ResourceDto> resourceDtoList = new ArrayList<>();

        for(Resource resource: resourceList){
            String startDate = resource.getStartDate().getYear() + "/" + resource.getStartDate().getMonthValue() + "/" + resource.getStartDate().getDayOfMonth();
            String endDate = resource.getEndDate().getYear() + "/" + resource.getEndDate().getMonthValue() + "/" + resource.getEndDate().getDayOfMonth();
            ResourceDto resourceDto = new ResourceDto(resource.getUser().getUserName(), resource.getRole().getName(), startDate, endDate, resource.getCostPerHour(), resource.getPercentageOfAllocation());
            resourceDtoList.add(resourceDto);
        }
        return resourceDtoList;
    }


}
