package switch2021.project.model.Resource;

import lombok.Getter;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectRole;

import java.time.LocalDate;

@Getter
public class ResourceReeng {


    private ResourceId id;
    private PercentageOfAllocation allocation;
    private CostPerHour cost;
    private ProjectRole role;
    private LocalDate startDate;
    private LocalDate endDate;

    public ResourceReeng(ResourceId id) {
        this.id = id;
    }

    public boolean isActiveToThisDate(LocalDate date) {

        boolean result = false;

        if(this.startDate.isBefore(date) || this.startDate.isEqual(date)){

            if(this.endDate.isEqual(date) || this.endDate.isAfter(date)){
                result = true;
            }

        }

        return result;
    }

}
