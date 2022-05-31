package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.jpa.TypologyJpa;
import switch2021.project.dataModel.assembler.TypologyJpaAssembler;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.persistence.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TypologyRepository implements ITypologyRepo {

    @Autowired
    TypologyJpaRepository jpaRepository;
    @Autowired
    TypologyJpaAssembler assembler;


    @Override
    public Optional<Typology> findByTypologyId(TypologyID typoId) {
        Optional<TypologyJpa> opTypology = jpaRepository.findById(typoId);
        Optional<Typology> typology = Optional.empty();

        if (opTypology.isPresent()) {
            typology = Optional.of(assembler.toDomain(opTypology.get()));
        }
        return typology;
    }

    @Override
    public List<Typology> findAll() {
        List<TypologyJpa> jpaList = jpaRepository.findAll();
        List<Typology> typologyList = new ArrayList<>();

        for (TypologyJpa i : jpaList) {
            typologyList.add(assembler.toDomain(i));
        }
        return typologyList;
    }

    @Override
    public boolean save(Typology typology) {
        TypologyJpa typologyJpa = assembler.toData(typology);

        if (!jpaRepository.existsById(typologyJpa.getId())) {
            jpaRepository.save(typologyJpa);
            return true;
        }

        return false;
    }

    @Override
    public boolean existsByTypologyId(TypologyID typoId) {
        return jpaRepository.existsById(typoId);
    }

    @Override
    public boolean deleteByTypologyId(TypologyID typoId) {

        if (jpaRepository.existsById(typoId)) {
            jpaRepository.deleteById(typoId);
            return true;
        }

        return false;
    }
}
