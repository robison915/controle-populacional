package quarkus.client.cotacao;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParams;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/json")
@RegisterRestClient(configKey = "app-cotacao-url")
//@RegisterProvider(LoggingFilter.class)
public interface CotacaoClient {

    @GET
    @Path("/{moeda}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ClientHeaderParams(value = {
            //@ClientHeaderParam(name="user-agent", value="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
            @ClientHeaderParam(name="user-agent", value="curl/7.54")
    })
    Optional<List<CotacaoDto>> cotacao(@PathParam String moeda);

}