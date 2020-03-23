package quarkus.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Estado {

    @NonNull
    @Id
    private String uf;

    @NonNull
    private String nome;

    @ToString.Exclude
    @OneToMany(mappedBy = "estado",fetch = FetchType.EAGER)
    private List<Cidade> cidades;

    @NonNull
    private Boolean selecionado;
}
