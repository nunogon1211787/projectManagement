package switch2021.project.datamodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.model.valueObject.TypologyID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "typologies")
@Getter @Setter
public class TypologyJpa {

    @EmbeddedId
    private TypologyID id;

    public TypologyJpa(TypologyID id) {
        this.id = id;
    }
}
