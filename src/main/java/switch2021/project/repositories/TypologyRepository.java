package switch2021.project.repositories;

import lombok.Getter;
import switch2021.project.interfaces.TypologyRepositoryInterface;
import switch2021.project.model.Typology.Typology;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TypologyRepository implements TypologyRepositoryInterface {

    /**
     * Attributes
     **/
    private List<Typology> typologyList;


    /**
     * Typology Store Constructor
     **/
    public TypologyRepository() {
        typologyList = new ArrayList<>();
    }


    /**
     * Methods.
     */
    @Override
    public Typology findTypologyByDescription(String description) {
        Typology typo = null;

        for (Typology i : typologyList) {
            if (i.getId_description().hasDescription(description)) {
                typo = i;
                break;
            }
        }
        return typo;
    }

    @Override
    public List<Typology> findAllTypology() {
        return new ArrayList<>(typologyList);
    }

    @Override
    public boolean saveTypology(Typology typology) {
        boolean result = true;

        if (existsByDescription(typology.getId_description().getDescription().getText())) {
            result = false;
        } else{
            typologyList.add(typology);
        }
        return result;
    }

    @Override
    public boolean existsByDescription(String description) {
        for (Typology typo : typologyList) {
            if (typo.hasID_Description(description)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteTypology(String description) {
        List<Typology> temp = new ArrayList<>();

        for (Typology typo : typologyList) {
            if (!typo.hasID_Description(description)) {
                temp.add(typo);
            }
        }
        typologyList = temp;
    }
}