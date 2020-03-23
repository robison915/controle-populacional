package quarkus.handler;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.stream.Collectors;


@Provider
@ApplicationScoped
public class ConstraintViolationExceptionHandlerMapping implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException e) {
        System.out.println("Exception");
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getMessage());
        JsonArrayBuilder jab = Json.createArrayBuilder();
        e.getConstraintViolations().stream().forEach(s -> jab.add(s.getMessage()));
        e.getConstraintViolations().stream().forEach(s -> System.out.println(s.getMessage()));
        return Response.status(Response.Status.BAD_REQUEST).entity(
                Json.createObjectBuilder()
//                        .add("message",(e.getMessage()==null?"Desconhecido":e.getMessage()).split(":")[1])
                        .add("messages",jab)
                        .add("class",e.getClass().getCanonicalName())
                        .build())
                .build();
    }
}
