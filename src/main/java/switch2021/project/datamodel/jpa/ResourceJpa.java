package switch2021.project.datamodel.jpa;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
