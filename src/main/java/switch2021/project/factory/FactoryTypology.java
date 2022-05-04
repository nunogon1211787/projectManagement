package switch2021.project.factory;


import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IFactoryTypology;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ID_Typology;

public class FactoryTypology implements IFactoryTypology {
    @Override
    public Typology createTypology(TypologyDTO dto) {
        Description des = new Description(dto.getDescription());
        ID_Typology id = new ID_Typology(des);
        return new Typology(id);
    }
}
