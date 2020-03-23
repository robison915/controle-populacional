package quarkus.handler;

import io.quarkus.arc.ArcUndeclaredThrowableException;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
//io.quarkus.arc.ArcUndeclaredThrowableException: Error invoking subclass method

@Provider
@ApplicationScoped
public class ArcUndeclaredThrowableExceptionExceptionHandlerMapping implements ExceptionMapper<ArcUndeclaredThrowableException>{

    @Override
    public Response toResponse(ArcUndeclaredThrowableException e) {
        Throwable t = getCause(e.getCause());
        String message;
        if(t != null && t.getMessage().contains("Unique")){
            message = "Já existe uma cidade com este nome";
        }else {
            message = e.getMessage();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(
                Json.createObjectBuilder()
                        .add("message",message)
                        .add("class",e.getClass().getCanonicalName())
                        .build())
                .build();
    }

    private Throwable getCause(Throwable t){
        if(t.getMessage().contains("Unique index")){
            return new Throwable("Unique");
        }else if (t.getCause() == null){
            return null;
        }
        Throwable t1 = getCause(t.getCause());
        if(t1 == null){
            return t;
        }
        return t1;
    }
}
