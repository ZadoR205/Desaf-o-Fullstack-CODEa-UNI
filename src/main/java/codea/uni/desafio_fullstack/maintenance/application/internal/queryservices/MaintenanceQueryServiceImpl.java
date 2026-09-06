package codea.uni.desafio_fullstack.maintenance.application.internal.queryservices;

import codea.uni.desafio_fullstack.maintenance.domain.model.aggregates.Maintenance;
import codea.uni.desafio_fullstack.maintenance.domain.model.queries.*;
import codea.uni.desafio_fullstack.maintenance.domain.services.MaintenanceQueryService;
import codea.uni.desafio_fullstack.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MaintenanceQueryServiceImpl implements MaintenanceQueryService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceQueryServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> handle(GetAllMaintenancesQuery query) {
        return this.maintenanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> handle(GetMaintenanceByIdQuery query) {
        return this.maintenanceRepository.findById(query.id());
    }

    @Override
    public List<Maintenance> handle(GetMaintenancesByMachineryCodeQuery query) {
        return this.maintenanceRepository.findAllByMachineryCode(query.machineryCode().trim());
    }

    @Override
    public List<Maintenance> handle(GetMaintenancesByDateRangeQuery query) {
        return this.maintenanceRepository.findAllByDateBetween(query.startDate(), query.endDate());
    }

    @Override
    public List<Maintenance> handle(GetMaintenancesByOperatorIdQuery query) {
        return this.maintenanceRepository.findAllByOperatorId(query.operatorId());
    }
}
