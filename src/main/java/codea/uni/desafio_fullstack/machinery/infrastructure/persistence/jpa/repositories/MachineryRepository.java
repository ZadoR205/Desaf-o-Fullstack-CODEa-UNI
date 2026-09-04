package codea.uni.desafio_fullstack.machinery.infrastructure.persistence.jpa.repositories;

import codea.uni.desafio_fullstack.machinery.domain.model.aggregates.Machinery;
import codea.uni.desafio_fullstack.machinery.domain.model.entities.MachineryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineryRepository extends JpaRepository <Machinery, String> {
    List<Machinery> findAll();
    Optional<Machinery> findMachineryByCode(String machineryCode);
    List<Machinery> findAllByState(boolean state);

    List<Machinery> findAllByMachineryType(MachineryType machineryType);
}
