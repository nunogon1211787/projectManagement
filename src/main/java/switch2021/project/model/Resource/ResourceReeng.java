package switch2021.project.model.Resource;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectRoleReeng;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class ResourceReeng {


    private ResourceIDReeng id;
    private PercentageOfAllocation allocation;
    private CostPerHour cost;
    private ProjectRoleReeng role;
    private LocalDate endDate;

    public ResourceReeng(ResourceIDReeng id) {
        this.id = id;
    }

    public boolean isActiveToThisDate(LocalDate date) {

        boolean result = false;

        if(this.id.getStartDate().isBefore(date) || this.id.getStartDate().isEqual(date)){

            if(this.endDate.isEqual(date) || this.endDate.isAfter(date)){
                result = true;
            }

        }

        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceReeng that = (ResourceReeng) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
