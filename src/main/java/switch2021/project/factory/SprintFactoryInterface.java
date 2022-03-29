package switch2021.project.factory;

import switch2021.project.model.Project.Sprint;

import java.time.LocalDate;

public interface SprintFactoryInterface {

    Sprint createSprint(String name, LocalDate startDate);
}
