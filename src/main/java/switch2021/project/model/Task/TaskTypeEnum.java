package switch2021.project.model.Task;

import java.util.ArrayList;
import java.util.List;

public enum TaskTypeEnum {

    Meeting,
    Documentation,
    Design,
    Implementation,
    Testing,
    Deployment;



    public static List<String> getTaskTypesDescriptionEnums() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        List<String> valoresString = new ArrayList<>();


        for(TaskTypeEnum taskTypeEnum : valoresType) {
            valoresString.add(taskTypeEnum.toString());
        }

        return valoresString;
    }

}
