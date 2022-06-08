package switch2021.project.entities.valueObjects.vos.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum TaskTypeEnum {

    MEETING,
    DOCUMENTATION,
    DESIGN,
    IMPLEMENTATION,
    TESTING,
    DEPLOYMENT;

    public static List<String> getTaskTypesDescriptionEnums() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        List<String> valoresString = new ArrayList<>();

        for(TaskTypeEnum taskTypeEnum : valoresType) {
            valoresString.add(taskTypeEnum.toString());
        }
        return valoresString;
    }
}
