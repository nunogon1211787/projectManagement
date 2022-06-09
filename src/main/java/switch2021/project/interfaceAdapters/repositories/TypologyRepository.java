package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.jpa.TypologyJpa;
import switch2021.project.dataModel.assembler.TypologyJpaAssembler;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.persistence.TypologyJpaRepository;

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

        if(opTypology.isPresent()) {
            TypologyJpa typologyJpa = opTypology.get();
            Typology typology = assembler.toDomain(typologyJpa);

            return Optional.of(typology);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Typology> findAll() {
        List<TypologyJpa> jpaList = jpaRepository.findAll();
        return assembler.toDomain(jpaList);
    }

    @Override
    public Typology save(Typology typology) {
        TypologyJpa typologyJpa = assembler.toData(typology);

        TypologyJpa savedTypologyJpa = jpaRepository.saveAndFlush(typologyJpa);

        return assembler.toDomain(savedTypologyJpa);
    }

    @Override
    public boolean existsByTypologyId(TypologyID typoId) {
        return jpaRepository.existsById(typoId);
    }

    @Override
    public void deleteByTypologyId(TypologyID typoId) {

        if (!jpaRepository.existsById(typoId)) {
            throw new NullPointerException("Typology does not exists!");
        }
            jpaRepository.deleteById(typoId);
    }
}
