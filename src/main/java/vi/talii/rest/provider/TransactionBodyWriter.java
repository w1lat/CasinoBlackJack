package vi.talii.rest.provider;


import com.fasterxml.jackson.databind.ObjectMapper;
import vi.talii.model.to.Transaction;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces("application/json")
public class TransactionBodyWriter implements MessageBodyWriter<Transaction>{

    private static final transient ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return aClass == Transaction.class;
    }

    @Override
    public long getSize(Transaction transaction, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Transaction transaction, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        MAPPER.writeValue(outputStream, transaction);
    }
}