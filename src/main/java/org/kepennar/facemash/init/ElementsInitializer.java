package org.kepennar.facemash.init;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.repository.ElementRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

@Named("elementsInitializer")
public class ElementsInitializer implements InitializingBean {

    @Inject
    @Named("elementRepository")
    private ElementRepository elementRepository;

    @Value("${images.directory}")
    public String imgDirectory;
    
    @Override
	public void afterPropertiesSet() {
    	elementRepository.deleteAll();
        elementRepository.save(new Element("Eloise", imgDirectory + "/1.jpg", "Girls one"));
        elementRepository.save(new Element("Blondy", imgDirectory + "/2.jpg", "Girls two"));
        elementRepository.save(new Element("Aaana", imgDirectory + "/3.jpg", "Girls three"));
        elementRepository.save(new Element("strgEyes", imgDirectory + "/4.jpg", "Girls four"));
        elementRepository.save(new Element("Angy", imgDirectory + "/5.jpg", "Girls five"));
        elementRepository.save(new Element("B&w", imgDirectory + "/6.jpg", "Girls six"));
        elementRepository.save(new Element("Lima", imgDirectory + "/7.jpg", "Girls seven"));
        elementRepository.save(new Element("Port", imgDirectory + "/8.jpg", "Girls height"));
    	
     
    }
}
