package codea.uni.desafio_fullstack.machinery.infrastructure.persistence.jpa.repositories;

import codea.uni.desafio_fullstack.machinery.domain.model.aggregates.Machinery;
import codea.uni.desafio_fullstack.machinery.domain.model.entities.MachineryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineryRepository extends JpaRepository <Machinery, String> {
    List<Machinery> findAll();
    List<Machinery> findMachineryByCode(String machineryCode);
    List<Machinery> findMachineryByMachineryType(MachineryType type);
    List<Machinery> findAllByState(boolean state);

}
