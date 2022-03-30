package switch2021.project.factoryInterface;

import switch2021.project.model.Sprint.Sprint;

import java.time.LocalDate;

public interface SprintFactoryInterface {
    Sprint createSprint(String name, LocalDate startDate);
}
