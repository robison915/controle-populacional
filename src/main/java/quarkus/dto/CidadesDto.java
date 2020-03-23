package quarkus.dto;

import lombok.*;
import quarkus.domain.Cidade;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadesDto {
    private Cidade cidade;
    private BigDecimal custoPopulacional;
    private BigDecimal custoPopulacionalCotacao;
}
