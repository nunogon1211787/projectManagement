package switch2021.project.model.Resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
public class ResourceIDReeng {

        private SystemUserID user;
        private ProjectID project;
        private LocalDate startDate;

        public ResourceIDReeng(SystemUserID user, ProjectID project, LocalDate startDate) {
            this.user = user;
            this.project = project;
            this.startDate = startDate;
        }
    }
