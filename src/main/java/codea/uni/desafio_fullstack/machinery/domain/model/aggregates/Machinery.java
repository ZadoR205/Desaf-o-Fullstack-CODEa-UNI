package codea.uni.desafio_fullstack.machinery.domain.model.aggregates;

import codea.uni.desafio_fullstack.machinery.domain.model.entities.MachineryType;
import codea.uni.desafio_fullstack.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="machinery")
@Setter
@Getter
public class Machinery extends AuditableAbstractAggregateRoot<Machinery> {
    @Id
    @Column(nullable = false,unique = true, length = 20)
    private String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private MachineryType machineryType;

    @Column(nullable = false, name="hour_meter")
    private float hourMeter = 0.0f;

    @Column(nullable = false)
    private boolean state = true; // true: ACTIVO, false: BLOQUEADO
}
