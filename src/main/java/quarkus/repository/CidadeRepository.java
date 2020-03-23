package quarkus.repository;

import quarkus.domain.Cidade;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {

    List<Cidade> findByNome(String nome);
}
