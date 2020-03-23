package quarkus.facade;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import quarkus.domain.Cidade;
import quarkus.domain.Estado;
import quarkus.dto.CidadesDto;
import quarkus.dto.InCidadeDto;
import quarkus.repository.CidadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CidadeFacade {

    @ConfigProperty(name = "app-custo-percentual")
    private BigDecimal percentualCusto;
    @ConfigProperty(name = "app-custo-individual")
    private BigDecimal individualCusto;
    @ConfigProperty(name = "app-custo-corte")
    private BigDecimal corteCusto;
    @Inject
    CotacaoFacade cotacaoFacade;
    @Inject
    CidadeRepository cidadeRepository;
    @Inject
    EstadoFacade estadoFacade;

    private BigDecimal cotacao;

    public CidadesDto custoPolulacional(Cidade cidade){
        BigDecimal valor;
        if(cidade.getPopulacao().compareTo(corteCusto) <= 0){
            valor = cidade.getPopulacao().multiply(individualCusto);
            return CidadesDto.builder()
                    .cidade(cidade)
                    .custoPopulacional(valor)
                    .build();
        }else{
            BigDecimal baixoPercentual = cidade.getPopulacao().subtract(corteCusto);
            BigDecimal altoPercentual = corteCusto;
            BigDecimal valorDesconto =  individualCusto.multiply(percentualCusto.divide(BigDecimal.valueOf(100)));
            valor = baixoPercentual.multiply(valorDesconto).add(altoPercentual.multiply(individualCusto));
            return CidadesDto.builder()
                    .cidade(cidade)
                    .custoPopulacional(valor)
                    .build();
        }
    }

    private CidadesDto converteCotacao(CidadesDto cidadesDto ){
        cidadesDto.setCustoPopulacionalCotacao(cidadesDto.getCustoPopulacional().multiply(cotacao));
        return cidadesDto;
    }

    public List<CidadesDto> converteCotacao(List<Cidade> cidades){
        cotacao = cotacaoFacade.buscarCotacao();
        return cidades.stream()
                .map(this::custoPolulacional)
                .map(this::converteCotacao)
                .collect(Collectors.toList());
    }

    public void addCidade(InCidadeDto dto){
        System.out.println(dto);
        Estado estado = estadoFacade.buscarEstado(dto.getEstado());



        Cidade cidade = Cidade.builder()
                .estado(estado)
                .nome(dto.getNome())
                .populacao(dto.getPopulacao())
                .build();

        cidadeRepository.save(cidade);

    }

}
