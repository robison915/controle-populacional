package quarkus.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoDto {
    private String nome;
    private String uf;
    private Boolean selecionado;
}
