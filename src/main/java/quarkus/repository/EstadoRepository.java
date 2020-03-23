package quarkus.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import quarkus.domain.Estado;
import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado,Long> {
    Estado findByNome(String nome);
    Estado findByUf(String uf);
    List<Estado> findAll();

    //    @Query("select new teste.dto.EstadoDto(e.nome,e.uf,e.selecionado) from Estado e")
    @Query("select e from Estado e")
    List<Estado> buscarEstados();
}
