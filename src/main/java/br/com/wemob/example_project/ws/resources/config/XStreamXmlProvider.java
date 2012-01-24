package br.com.wemob.example_project.ws.resources.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.core.provider.AbstractMessageReaderWriterProvider;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Provider
public class XStreamXmlProvider extends AbstractMessageReaderWriterProvider<Object> {
    
	private static final Set<Class<?>> processed = new HashSet<Class<?>>();
    private static final XStream xstream = new XStream();
    private static final String DEFAULT_ENCODING = "utf-8";
    
    // Static initializer
    {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.autodetectAnnotations(true);
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

    @SuppressWarnings("rawtypes")
	protected XStream getXStream(Class<?> type,Object object) {
        synchronized (processed) {
        	if(object instanceof List){
    			List col = (List) object;
            	if(!processed.contains(col.get(0).getClass()))
            		xstream.processAnnotations(col.get(0).getClass());
            }
        	
            if (!processed.contains(type)) {
                xstream.processAnnotations(type);
                processed.add(type);
            }
        }
        return xstream;
    } 

    public Object readFrom(Class<Object> aClass, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream)
            throws IOException, WebApplicationException  {
        String encoding = getCharsetAsString(mediaType);
        XStream xStream = getXStream(aClass,null);
        return xStream.fromXML(new InputStreamReader(stream, encoding));
    }

    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> map, OutputStream stream)
            throws IOException, WebApplicationException {
        String encoding = getCharsetAsString(mediaType);
        
        XStream xStream = getXStream(o.getClass(),o);
        
        xStream.marshal(o, new CompactWriter(new OutputStreamWriter(stream, encoding)));
    }
}
