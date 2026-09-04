package codea.uni.desafio_fullstack.machinery.domain.services;

import codea.uni.desafio_fullstack.machinery.domain.model.aggregates.Machinery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetAllMachineryByMachineryTypeIdQuery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetAllMachineryByStateQuery;
import codea.uni.desafio_fullstack.machinery.domain.model.queries.GetMachineryByCodeQuery;

import java.util.List;
import java.util.Optional;

public interface MachineryQueryService {
    List<Machinery> handle(GetAllMachineryByMachineryTypeIdQuery query);
    List<Machinery> handle(GetAllMachineryByStateQuery query);
    Optional<Machinery> handle(GetMachineryByCodeQuery query);
}
