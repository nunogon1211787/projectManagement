package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectSettingsTest{

    /**
     *       >>>>>TYPOLOGY´S TEST<<<<<
     **/

    /** Este método faz teste do construtorr de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology() **/
    @Test
    public void constructorProjectSettingsTest() {
        //Input
        ProjectSettings Lists = new ProjectSettings();
        List<Typology> TypologyList = Lists.getArrayTypology();
        Typology TypoTest = Lists.createTypology("Test");
        Lists.saveTypology(TypoTest);
        //Expected
        List<Typology> ExpectedList = new ArrayList<>();
        ExpectedList.add(new Typology("Fixed Cost"));
        ExpectedList.add(new Typology("Time and Materials"));
        ExpectedList.add(new Typology("Test"));
        //Result
        assertEquals(ExpectedList.size(),TypologyList.size());
        assertEquals(ExpectedList.get(2), TypologyList.get(2));
    }

}
