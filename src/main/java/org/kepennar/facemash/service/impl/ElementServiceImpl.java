package org.kepennar.facemash.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.mvc.model.Fight;
import org.kepennar.facemash.repository.ElementRepository;
import org.kepennar.facemash.service.ElementService;
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

	@Value("${webapp.directory}")
	public String webAppDir;
	@Value("${images.directory}")
	public String imagesDir;
	@Value("${no_picture.image}")
	public String noPicture;
	
	
	private ElementRepository elementRepository;

	@Inject
    @Named("elementRepository")
	public void setElementRepository(ElementRepository elementRepository) {
		this.elementRepository = elementRepository;
	}

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
		Pageable pageRequest = new PageRequest(0, 5, Direction.ASC, "played");
		List<Element> elements = elementRepository.findAll(pageRequest).getContent();
		Integer random1 = (int) (Math.random()*5);
		Integer random2= null;
		do {
			random2 = (int) (Math.random()*5);
		} while (random1 == random2);
		return new Fight(elements.get(random1), elements.get(random2));
		
	}

	@Override
	@Transactional
	public Element createElement(String name, String description, MultipartFile file) {
		Element elem = null;
		if (file == null || file.getSize() == 0) {
			elem = new Element(name, imagesDir + noPicture, description);
		} else {
		
			String dirPath = webAppDir + imagesDir;
			File f = new File(dirPath);
			String[] names = f.list();
			int max = 0;
			for (String fileName : names) {
				String[] splittedFileName = fileName.split("\\.");
				if(splittedFileName.length >0) {
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
			
			FileOutputStream fileOut;
			String pictureName = null;
			try {
				int imgNumber = ++max;
				String imgPath = dirPath+ "/" + imgNumber + ".jpg";
				fileOut = new FileOutputStream(imgPath);
				fileOut.write(file.getBytes());
				pictureName = "/" + imgNumber +".jpg";
				fileOut.close();
				
			} catch (IOException  e) {
				e.printStackTrace();
			}
			elem = new Element(name, imagesDir + pictureName, description);
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

}
