package switch2021.project.factory;

import switch2021.project.model.Sprint;

import java.time.LocalDate;

public class SprintFactory implements SprintFactoryInterface {

    @Override
    public Sprint createSprint(String name, LocalDate startDate) {
        return new Sprint(name, startDate);
    }
}
