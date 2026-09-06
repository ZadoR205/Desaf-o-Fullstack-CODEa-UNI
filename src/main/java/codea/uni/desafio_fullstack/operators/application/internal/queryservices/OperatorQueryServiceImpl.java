package codea.uni.desafio_fullstack.operators.application.internal.queryservices;

import codea.uni.desafio_fullstack.operators.domain.model.aggregates.Operator;
import codea.uni.desafio_fullstack.operators.domain.model.queries.GetAllOperatorsQuery;
import codea.uni.desafio_fullstack.operators.domain.model.queries.GetOperatorByIdQuery;
import codea.uni.desafio_fullstack.operators.domain.model.queries.GetOperatorsByMachineryTypeCertificationQuery;
import codea.uni.desafio_fullstack.operators.domain.services.OperatorQueryService;
import codea.uni.desafio_fullstack.operators.infrastructure.persistence.jpa.repositories.OperatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OperatorQueryServiceImpl implements OperatorQueryService {

    private final OperatorRepository operatorRepository;

    public OperatorQueryServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public Optional<Operator> handle(GetOperatorByIdQuery query) {
        return this.operatorRepository.findById(query.id());
    }

    @Override
    public List<Operator> handle(GetAllOperatorsQuery query) {
        return this.operatorRepository.findAll();
    }

    @Override
    public List<Operator> handle(GetOperatorsByMachineryTypeCertificationQuery query) {
        return this.operatorRepository.findDistinctByCertificationsIdMachineryType(query.machineryTypeId());
    }
}
