package switch2021.project.factory;

import switch2021.project.model.Sprint;

import java.time.LocalDate;

public class SprintFactory {
    private Sprint sprint;

    public Sprint createSprint(String name, LocalDate startDate) {
        return this.sprint = new Sprint(name, startDate);
    }
}
