package codea.uni.desafio_fullstack.machinery.application.internal.queryservices;

import codea.uni.desafio_fullstack.machinery.domain.model.aggregates.Machinery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetAllMachineryByMachineryTypeIdQuery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetAllMachineryByStateQuery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetMachineryByCodeQuery;
import codea.uni.desafio_fullstack.machinery.domain.services.MachineryQueryService;
import codea.uni.desafio_fullstack.machinery.infrastructure.persistence.jpa.repositories.MachineryRepository;
import codea.uni.desafio_fullstack.machinery.infrastructure.persistence.jpa.repositories.MachineryTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineryQueryServiceImpl implements MachineryQueryService {
    private final MachineryRepository machineryRepository;
    private final MachineryTypeRepository machineryTypeRepository;

    public MachineryQueryServiceImpl(MachineryRepository machineryRepository,MachineryTypeRepository machineryTypeRepository) {
        this.machineryRepository = machineryRepository;
        this.machineryTypeRepository = machineryTypeRepository;
    }

    @Override
    public List<Machinery> handle(GetAllMachineryByMachineryTypeIdQuery query) {

        var machineryType = this.machineryTypeRepository.findById(query.machineryTypeId()).orElse(null);
        return this.machineryRepository.findAllByMachineryType(machineryType);
    }

    @Override
    public List<Machinery> handle(GetAllMachineryByStateQuery query) {
        return this.machineryRepository.findAllByState(query.state());
    }

    @Override
    public Optional<Machinery> handle(GetMachineryByCodeQuery query) {
        return this.machineryRepository.findMachineryByCode(query.code());
    }
}
