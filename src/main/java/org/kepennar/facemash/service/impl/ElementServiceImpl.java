package org.kepennar.facemash.service.impl;

import static org.kepennar.facemash.util.Constantes.NB_ELEMENTS_FOR_RANDOM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.exception.TechnicalException;
import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.mvc.model.Fight;
import org.kepennar.facemash.repository.ElementRepository;
import org.kepennar.facemash.service.ElementService;
import org.kepennar.facemash.service.SearchElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("elementService")
@Transactional(readOnly= true)
public class ElementServiceImpl implements ElementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementServiceImpl.class);
	
	public @Value("${webapp.directory}") String webAppDir;
	public @Value("${images.directory}") String imagesDir;
	public @Value("${no_picture.image}") String noPicture;
	public @Value("${family}") String family;
	
	@Inject @Named("elementRepository")
	private ElementRepository elementRepository;
	@Inject @Named("searchElementService")
	private SearchElementService searchElementService;
	
	
	@Override
	public Page<Element> getTopRated(int pageNumber, int pageSize) {
		Pageable pageRequest = new PageRequest(pageNumber, pageSize, Direction.DESC, "score");
		
		return elementRepository.findAll(pageRequest);
	}

	
	@Override
	public Page<Element> getWorstRated(int pageNumber, int pageSize) {
		Pageable pageRequest = new PageRequest(pageNumber, pageSize, Direction.ASC, "score");
		
		return elementRepository.findAll(pageRequest);
	}

	
	@Override
	public Element getWinner() {
		Pageable pageRequest = new PageRequest(0, 1, Direction.DESC, "score");
		
		Page<Element> page = elementRepository.findAll(pageRequest);
		return page.getContent().get(0);
	}

	
	@Override
	public Fight getFight() {
		Pageable pageRequest = new PageRequest(0, NB_ELEMENTS_FOR_RANDOM, Direction.ASC, "played");
		List<Element> elements = elementRepository.findAll(pageRequest).getContent();
		Integer random1 = (int) (Math.random()* NB_ELEMENTS_FOR_RANDOM);
		Integer random2= null;
		do {
			random2 = (int) (Math.random()* NB_ELEMENTS_FOR_RANDOM);
		} while (random1.equals(random2));
		return new Fight(elements.get(random1), elements.get(random2));
		
	}

	@Override
	@Transactional
	public Element createElement(String name, String description, MultipartFile file) {
		Element elem = null;
		if (file == null || file.getSize() == 0) {
			elem = new Element(family, name, imagesDir + "/" + noPicture, description);
		} else {
		
			String dirPath = webAppDir + imagesDir;
			File f = new File(dirPath);
			
			int max = getLastImgNumber(f.list());
			
			FileOutputStream fileOut;
			String pictureName = null;
			try {
				int imgNumber = ++max;
				String imgPath = dirPath+ "/" + imgNumber + ".jpg";
				fileOut = new FileOutputStream(imgPath);
				fileOut.write(file.getBytes());
				pictureName = "/" + imgNumber +".jpg";
				fileOut.close();
			} catch (IOException e) {
				LOGGER.error("Error saving element picture", e);
				throw new TechnicalException(e);
			}
			elem = new Element(family, name, imagesDir + pictureName, description);
		}
		elementRepository.save(elem);
		return elem;
	}
	
	
	@Override
	public int getTotalPlayed() {
		List<Element> elements = elementRepository.findAll();
		int totalPLayed = 0;
		for (Element element : elements) {
			totalPLayed += element.getPlayed();
		}
		return totalPLayed != 0 ? totalPLayed/2 : totalPLayed;
	}

	
	private int getLastImgNumber(String... imgNames) {
		int max = 0;
		for (String fileName : imgNames) {
			String[] splittedFileName = fileName.split("\\.");
			if(splittedFileName.length > 0) {
				try {
					Integer imgNumber = Integer.valueOf(splittedFileName[0]);
					if ( imgNumber != null && imgNumber.compareTo(max) > 0) {
						max = imgNumber;
					}
				} catch (NumberFormatException e) {
					continue;
				}
				
			}
		}
		return max;
	}
	

}
