package vi.talii.rest.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vi.talii.exception.GameException;
import vi.talii.model.to.ErrorInfo;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by serhii on 26.10.15.
 */@Provider
public class GenericExceptionMapper implements ExceptionMapper<GameException> { // todo use generic for own class


   /* @Override
    public Response toResponse(Throwable throwable) {
            return Response.status(500).entity(throwable.getMessage()).type("application/json").build();
    }*/

    private static final transient ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Response toResponse(final GameException exception)
    {
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST)
                .entity(defaultJSON(exception))
                .type(MediaType.APPLICATION_JSON);
        return builder.build();
    }

    private String defaultJSON(final GameException exception)
    {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(),"test"); // todo add user message

        try
        {
            return MAPPER.writeValueAsString(errorInfo);
        }
        catch (JsonProcessingException e)
        {
            return "{\"message\":\"An internal error occurred\"}";
        }
    }
}
