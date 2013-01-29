package org.kepennar.facemash.util;

import org.kepennar.facemash.model.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElementUtils {

	public @Value("${no_picture.image}") String noPicture;
	public @Value("${images.directory}") String imagesDir;
	public @Value("${family}") String family;
	
	public Element newNoResultElement() {
		return new Element(FamilyEnum.getFamilyEnumFromKey(family).getKey(), "No result", imagesDir + "/" + noPicture, "No result");
		
	}

}
