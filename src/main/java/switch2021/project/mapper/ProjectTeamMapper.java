
package switch2021.project.mapper;

import switch2021.project.dto.ResourceDto;
import switch2021.project.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ProjectTeamMapper {

    /**
     * Method to change data in to a Resource DTO
     **/

    public ResourceDto toDto(Resource resource){
        String startDate = resource.getStartDate().getYear() + "/" + resource.getStartDate().getMonthValue() + "/" + resource.getStartDate().getDayOfMonth();
        String endDate = resource.getEndDate().getYear() + "/" + resource.getEndDate().getMonthValue() + "/" + resource.getEndDate().getDayOfMonth();
        String role = null;
        if(resource.getRole() != null){
            role = resource.getRole().getName();
        }
        return new ResourceDto(resource.getUser().getUserName(),role, startDate, endDate, resource.getCostPerHour(), resource.getPercentageOfAllocation());
    }

    /**
     * Method to change data in to a Resource DTO List
     **/

    public List<ResourceDto> toDto(List<Resource> resourceList){
        List<ResourceDto> resourceDtoList = new ArrayList<>();

        for(Resource resource: resourceList){
            String startDate = resource.getStartDate().getYear() + "/" + resource.getStartDate().getMonthValue() + "/" + resource.getStartDate().getDayOfMonth();
            String endDate = resource.getEndDate().getYear() + "/" + resource.getEndDate().getMonthValue() + "/" + resource.getEndDate().getDayOfMonth();
            String role = null;
            if(resource.getRole() != null){
                role = resource.getRole().getName();
            }
            ResourceDto resourceDto = new ResourceDto(resource.getUser().getUserName(), role, startDate, endDate, resource.getCostPerHour(), resource.getPercentageOfAllocation());
            resourceDtoList.add(resourceDto);
        }
        return resourceDtoList;
    }


}
