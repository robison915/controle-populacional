package quarkus.handler;

import io.quarkus.arc.ArcUndeclaredThrowableException;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class ExceptionHandlerMapping implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(
                Json.createObjectBuilder()
                        .add("message",e.getMessage())
                        .add("class",e.getClass().getCanonicalName())
                        .build())
                .build();
    }
}
