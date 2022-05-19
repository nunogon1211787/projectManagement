//package switch2021.project.repositories;
//
//import org.springframework.stereotype.Repository;
//import switch2021.project.interfaces.ITypologyRepo;
//import switch2021.project.model.Typology.Typology;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class TypologyRepository implements ITypologyRepo {
//
//    /**
//     * Attributes
//     **/
//    private List<Typology> typologyList;
//
//
//    /**
//     * Typology Store Constructor
//     **/
//    public TypologyRepository() {
//        typologyList = new ArrayList<>();
//    }
//
//
//    /**
//     * Methods.
//     */
//    public List<Typology> getTypologyList() {return this.typologyList;}
//
//    @Override
//    public Typology findTypologyById(String description) {
//        Typology typo = null;
//
//        for (Typology i : typologyList) {
//            if (i.getId_description().hasDescription(description)) {
//                typo = i;
//                break;
//            }
//        }
//        return typo;
//    }
//
//    @Override
//    public List<Typology> findAllTypology() {
//        return new ArrayList<>(typologyList);
//    }
//
//    @Override
//    public boolean saveTypology(Typology typology) {
//        boolean result = true;
//
//        if (existsByTypologyId(typology.getId_description().getDescription().getText())) {
//            result = false;
//        } else{
//            typologyList.add(typology);
//        }
//        return result;
//    }
//
//    @Override
//    public boolean existsByTypologyId(String description) {
//        for (Typology typo : typologyList) {
//            if (typo.hasID_Description(description)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void deleteTypology(String description) {
//        List<Typology> temp = new ArrayList<>();
//
//        for (Typology typo : typologyList) {
//            if (!typo.hasID_Description(description)) {
//                temp.add(typo);
//            }
//        }
//        typologyList = temp;
//    }
//}