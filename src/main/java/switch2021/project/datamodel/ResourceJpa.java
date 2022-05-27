package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectRoleReeng;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity(name = "ResourceJpa")
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResourceJpa {

    @EmbeddedId
    private ResourceIDJpa id;
    private String endDate;
    private double allocation;
    private double cost;
    private String role;
}
