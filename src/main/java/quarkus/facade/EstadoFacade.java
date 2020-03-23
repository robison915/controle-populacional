package quarkus.facade;

import quarkus.domain.Estado;
import quarkus.dto.CidadesDto;
import quarkus.dto.EstadoDto;
import quarkus.repository.EstadoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoFacade {

    @Inject
    CidadeFacade cidadeFacade;
    @Inject
    EstadoRepository estadoRepository;

    public List<EstadoDto> buscarEstados(){
        return estadoRepository.buscarEstados()
                .stream().map(s -> {
            return EstadoDto.builder()
                    .nome(s.getNome())
                    .selecionado(s.getSelecionado())
                    .uf(s.getUf())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<CidadesDto> buscarInformacoesEstados(String ufEstado){
        Estado estado = estadoRepository.findByUf(ufEstado);
        estado.getCidades();
        return cidadeFacade.converteCotacao(estado.getCidades());
    }

    public Estado buscarEstado(String uf){
        return estadoRepository.findByUf(uf);
    }




}
