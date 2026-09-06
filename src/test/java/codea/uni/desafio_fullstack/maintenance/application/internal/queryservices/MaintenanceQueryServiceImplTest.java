package codea.uni.desafio_fullstack.maintenance.application.internal.queryservices;

import codea.uni.desafio_fullstack.maintenance.domain.model.aggregates.Maintenance;
import codea.uni.desafio_fullstack.maintenance.domain.model.commands.CreateMaintenanceCommand;
import codea.uni.desafio_fullstack.maintenance.domain.model.queries.*;
import codea.uni.desafio_fullstack.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaintenanceQueryServiceImplTest {

    @Mock
    private MaintenanceRepository maintenanceRepository;

    private MaintenanceQueryServiceImpl maintenanceQueryService;

    @BeforeEach
    void setUp() {
        maintenanceQueryService = new MaintenanceQueryServiceImpl(maintenanceRepository);
    }

    private Maintenance createSampleMaintenance(String machineryCode, LocalDate date, UUID operatorId) {
        CreateMaintenanceCommand command = new CreateMaintenanceCommand(
                machineryCode,
                date,
                150.0f,
                operatorId,
                "Sample maintenance"
        );
        return new Maintenance(command);
    }

    @Test
    @DisplayName("Should get all maintenances")
    void shouldGetAllMaintenances() {
        UUID operatorId = UUID.randomUUID();
        Maintenance m1 = createSampleMaintenance("EQ-001", LocalDate.of(2026, 9, 1), operatorId);
        Maintenance m2 = createSampleMaintenance("EQ-002", LocalDate.of(2026, 9, 2), operatorId);

        when(maintenanceRepository.findAll()).thenReturn(List.of(m1, m2));

        var result = maintenanceQueryService.handle(new GetAllMaintenancesQuery());

        assertEquals(2, result.size());
        verify(maintenanceRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get maintenance by ID when exists")
    void shouldGetMaintenanceByIdWhenExists() {
        UUID maintenanceId = UUID.randomUUID();
        UUID operatorId = UUID.randomUUID();
        Maintenance m = createSampleMaintenance("EQ-001", LocalDate.of(2026, 9, 1), operatorId);

        when(maintenanceRepository.findById(maintenanceId)).thenReturn(Optional.of(m));

        var result = maintenanceQueryService.handle(new GetMaintenanceByIdQuery(maintenanceId));

        assertTrue(result.isPresent());
        assertEquals("EQ-001", result.get().getMachineryCode());
        verify(maintenanceRepository, times(1)).findById(maintenanceId);
    }

    @Test
    @DisplayName("Should return empty optional when maintenance by ID does not exist")
    void shouldReturnEmptyWhenMaintenanceByIdDoesNotExist() {
        UUID maintenanceId = UUID.randomUUID();

        when(maintenanceRepository.findById(maintenanceId)).thenReturn(Optional.empty());

        var result = maintenanceQueryService.handle(new GetMaintenanceByIdQuery(maintenanceId));

        assertTrue(result.isEmpty());
        verify(maintenanceRepository, times(1)).findById(maintenanceId);
    }

    @Test
    @DisplayName("Should get maintenances by machinery code")
    void shouldGetMaintenancesByMachineryCode() {
        UUID operatorId = UUID.randomUUID();
        Maintenance m1 = createSampleMaintenance("EQ-001", LocalDate.of(2026, 9, 1), operatorId);

        when(maintenanceRepository.findAllByMachineryCode("EQ-001")).thenReturn(List.of(m1));

        var result = maintenanceQueryService.handle(new GetMaintenancesByMachineryCodeQuery("EQ-001"));

        assertEquals(1, result.size());
        assertEquals("EQ-001", result.get(0).getMachineryCode());
        verify(maintenanceRepository, times(1)).findAllByMachineryCode("EQ-001");
    }

    @Test
    @DisplayName("Should get maintenances by operator ID")
    void shouldGetMaintenancesByOperatorId() {
        UUID operatorId = UUID.randomUUID();
        Maintenance m1 = createSampleMaintenance("EQ-001", LocalDate.of(2026, 9, 1), operatorId);

        when(maintenanceRepository.findAllByOperatorId(operatorId)).thenReturn(List.of(m1));

        var result = maintenanceQueryService.handle(new GetMaintenancesByOperatorIdQuery(operatorId));

        assertEquals(1, result.size());
        assertEquals(operatorId, result.get(0).getOperatorId());
        verify(maintenanceRepository, times(1)).findAllByOperatorId(operatorId);
    }

    @Test
    @DisplayName("Should get maintenances by date range")
    void shouldGetMaintenancesByDateRange() {
        UUID operatorId = UUID.randomUUID();
        LocalDate startDate = LocalDate.of(2026, 9, 1);
        LocalDate endDate = LocalDate.of(2026, 9, 30);
        Maintenance m1 = createSampleMaintenance("EQ-001", LocalDate.of(2026, 9, 15), operatorId);

        when(maintenanceRepository.findAllByDateBetween(startDate, endDate)).thenReturn(List.of(m1));

        var result = maintenanceQueryService.handle(new GetMaintenancesByDateRangeQuery(startDate, endDate));

        assertEquals(1, result.size());
        verify(maintenanceRepository, times(1)).findAllByDateBetween(startDate, endDate);
    }
}
