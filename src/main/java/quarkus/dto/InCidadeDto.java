package quarkus.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InCidadeDto {

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @NotNull(message = "Populacao não pode ser nulo")
    private BigDecimal populacao;

    @NotNull(message = "Estado não pode ser nulo")
    @NotBlank(message = "Estado não pode estar em branco")
    private String estado;
}
