package quarkus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "uf"})
})
public class Cidade {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private BigDecimal populacao;

    @JsonbTransient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf")
    private Estado estado;
}

