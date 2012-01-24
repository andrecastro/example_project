package br.com.wemob.example_project.ws.resources.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import com.sun.jersey.core.provider.AbstractMessageReaderWriterProvider;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Provider
public class XStreamJsonProvider extends AbstractMessageReaderWriterProvider<Object>
{
    private static final Set<Class<?>> processed = new HashSet<Class<?>>();
    private static final XStream xstreamIn = new XStream(new JettisonMappedXmlDriver());
    private static final XStream xstreamOut = new XStream(new JsonHierarchicalStreamDriver() {
	    public HierarchicalStreamWriter createWriter(Writer writer) {
	        return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
	    }
	});
    private static final String DEFAULT_ENCODING = "unicode";
    
    // Static Initializer
    {
        xstreamIn.setMode(XStream.NO_REFERENCES);
        xstreamOut.setMode(XStream.NO_REFERENCES);
        xstreamOut.autodetectAnnotations(true);
    }
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType arg3) {
    	return true;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType arg3) {
	return true;
    }

    protected static String getCharsetAsString(MediaType m) {
        if (m == null) {
            return DEFAULT_ENCODING;
        }
        String result = m.getParameters().get("charset");
        return (result == null) ? DEFAULT_ENCODING : result;
    }

    protected XStream getXStreamIn(Class<?> type) {
        synchronized (processed) {
            if (!processed.contains(type)) {
                xstreamIn.processAnnotations(type);
                processed.add(type);
            }
        }
        return xstreamIn;
    }
    
    public Object readFrom(Class<Object> aClass, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream)
            throws IOException, WebApplicationException {
        String encoding = getCharsetAsString(mediaType);
        XStream xStream = getXStreamIn(aClass);
        return xStream.fromXML(new InputStreamReader(stream, encoding));
    }	

    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> map, OutputStream stream)
            throws IOException, WebApplicationException {
        String encoding = getCharsetAsString(mediaType);
        xstreamOut.toXML(o, new OutputStreamWriter(stream, encoding));
    }
}