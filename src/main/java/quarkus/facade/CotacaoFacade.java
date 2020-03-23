package quarkus.facade;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import quarkus.client.cotacao.CotacaoClient;
import quarkus.client.cotacao.CotacaoDto;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Dependent
public class CotacaoFacade {

    @Inject
    @RestClient
    CotacaoClient cotacaoClient;
    @ConfigProperty(name = "app-cotacao-moeda")
    String moeda;

    private BigDecimal ultimaCotacao;

    public BigDecimal buscarCotacao(){
        Optional<List<CotacaoDto>> cotacao = cotacaoClient.cotacao(moeda);
        if(cotacao.isPresent()){
            ultimaCotacao = new BigDecimal(cotacao.get().get(0).getBid());
            return ultimaCotacao;
        }
        throw new NotFoundException("Cotação não encontrada");
    }

    public BigDecimal getUltimaCotacao() {
        return ultimaCotacao;
    }
}
