package quarkus.resource;

import quarkus.domain.Estado;
import quarkus.dto.CidadesDto;
import quarkus.dto.EstadoDto;
import quarkus.facade.CidadeFacade;
import quarkus.facade.EstadoFacade;
import quarkus.repository.EstadoRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/estado")
public class EstadoResource {

    @Inject
    EstadoRepository estadoRepository;
    @Inject
    EstadoFacade estadoFacade;

    @GET
    @Path("/")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<EstadoDto> getEstados(){
        //List<Estado> estados = estadoRepository.buscarEstados();
        return estadoRepository.buscarEstados()
                .stream().map(s -> {
            return EstadoDto.builder()
                    .nome(s.getNome())
                    .selecionado(s.getSelecionado())
                    .uf(s.getUf())
                    .build();
        }).collect(Collectors.toList());


        //return estadoFacade.buscarEstados();
//        return Arrays.asList(EstadoDto.builder()
//                .nome("teste")
//                .build());
    }

    @GET
    @Path("/{uf}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<CidadesDto> buscarInformacoesEstado(@PathParam("uf") String uf){
        return estadoFacade.buscarInformacoesEstados(uf);
//
//        Estado estado = estadoRepository.findByUf(uf);
//        estado.getCidades();
//        return estado.getCidades()
//                .stream()
//                .map(cidadeFacade::custoPolulacional)
//                .map(cidadeFacade::converteCotacao)
//                .collect(Collectors.toList());
    }
}
