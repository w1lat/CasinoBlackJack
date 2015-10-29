package vi.talii.rest.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vi.talii.exception.GameException;
import vi.talii.model.to.ErrorInfo;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<GameException> {


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
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(),"Sorry, " + exception.getMessage());

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
