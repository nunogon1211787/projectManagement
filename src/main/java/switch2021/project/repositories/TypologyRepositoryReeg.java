package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.TypologyJpa;
import switch2021.project.datamodel.assembler.TypologyDomainDataAssembler;
import switch2021.project.factory.TypologyFactory;
import switch2021.project.factory.TypologyIDFactory;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.TypologyID;
import switch2021.project.repositories.jpa.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TypologyRepositoryReeg implements ITypologyRepo {

    @Autowired
    TypologyJpaRepository jpaRepository;
    @Autowired
    TypologyDomainDataAssembler assembler;


    @Override
    public Typology findTypologyById(String description) {
        Optional<TypologyJpa> opTypology = jpaRepository.findById(new TypologyID(new Description(description)));
        Typology typology = null;

        if(opTypology.isPresent()) {
            typology = assembler.toDomain(opTypology.get());
        }
        return typology;
    }

    @Override
    public List<Typology> findAllTypology() {
        List<TypologyJpa> jpaList = jpaRepository.findAll();
        List<Typology> typologyList = new ArrayList<>();

        for (TypologyJpa i: jpaList) {
            typologyList.add(assembler.toDomain(i));
        }
        return typologyList;
    }

    @Override
    public boolean saveTypology(Typology typology) {
        TypologyJpa typologyJpa = assembler.toData(typology);
        if(jpaRepository.save(typologyJpa) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsByTypologyId(String description) {
        return jpaRepository.existsById(new TypologyID(new Description(description)));
    }

    @Override
    public void deleteTypology(String description) {
        TypologyID id = new TypologyID(new Description(description));
        jpaRepository.deleteById(id);
    }
}
