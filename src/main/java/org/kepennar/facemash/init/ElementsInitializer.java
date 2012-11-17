package org.kepennar.facemash.init;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingIterator;
import org.codehaus.jackson.map.ObjectMapper;
import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.repository.ElementRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;



@Named("elementsInitializer")
public class ElementsInitializer implements InitializingBean {

    @Inject @Named("elementRepository")
    private ElementRepository elementRepository;

    @Value("${images.directory}")
    public String imgDirectory;
    @Value("${init.file}")
    public String initfile;
    
    @Override
	public void afterPropertiesSet() {
    	
    	ObjectMapper mapper = new ObjectMapper();
	
		MappingIterator<Element> elementsIterator = null;
		try {
			InputStream in = this.getClass().getClassLoader()
                    .getResourceAsStream(initfile);
    		JsonParser parser = mapper.getJsonFactory().createJsonParser(in);
    		elementsIterator = mapper.readValues(parser, Element.class);  
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	elementRepository.deleteAll();

    	if (elementsIterator != null) {
    		while (elementsIterator.hasNext()) {
    			Element elem = elementsIterator.next();
    			String imgurl = elem.getImgUrl();
    			elem.setImgUrl(imgDirectory + imgurl);
    			
    			elementRepository.save(elem);
    		}
    	}
    }
}
