package quarkus.resource;

import quarkus.domain.Cidade;
import quarkus.domain.Estado;
import quarkus.dto.InCidadeDto;
import quarkus.facade.CidadeFacade;
import quarkus.repository.CidadeRepository;
import quarkus.repository.EstadoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/cidade")
@RequestScoped
public class CidadeResource {

    @Inject
    private CidadeRepository cidadeRepository;
    @Inject
    private EstadoRepository estadoRepository;
    @Inject
    CidadeFacade cidadeFacade;

    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(@Valid InCidadeDto cidade) {
        try{
            cidadeFacade.addCidade(cidade);
        }catch (Exception e){
            //log.log(Level.SEVERE,"Adicionar cidade",e);
            throw e;
        }
        return Response.accepted().build();
    }

    @PATCH
    @Path("/{id}/{populacao}")
    @Produces("application/json")
    public Cidade atualizar(@PathParam("id") Long id, @PathParam("populacao")BigDecimal populacao) {
        Cidade cidade = cidadeRepository.findById(id).get();
        cidade.setPopulacao(populacao);
        return cidadeRepository.save(cidade);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response atualizar(@PathParam("id") Long id) throws Exception {
        Cidade cidade = cidadeRepository.findById(id).get();
        if(cidade.getEstado().getUf().equals("RS")){
            throw new Exception(String.format("Cidades do %s não podem ser excluidas",cidade.getEstado().getNome()));
        }
        cidadeRepository.deleteById(id);
        return Response.accepted().build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Iterable<Cidade> find() {
        return cidadeRepository.findAll();
    }


}
