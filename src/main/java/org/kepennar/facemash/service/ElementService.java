package org.kepennar.facemash.service;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.mvc.model.Fight;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ElementService {

	/**
	 * Create an {@link Element}
	 * <p>
	 * 	<ul>
	 * 		<li>Save uploaded picture on disk</li>
	 * 		<li>Save {@link Element} entity in DB</li>
	 * 	</ul>
	 * </p>
	 * @param name {@link Element#name}
	 * @param description {@link Element#description}
	 * @param file The picture
	 * @return The created {@link Element}
	 */
	Element createElement(String name, String description, MultipartFile file);
	
	/**
	 * Return a {@link Page} of {@link Element}s define by <param>pageNumber</param> and <param>pageSize</param><br>
	 * {@link Element}s are ordered by score DESC
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Element> getTopRated(int pageNumber, int pageSize);
	
	/**
	 * Return a {@link Page} of {@link Element}s define by <param>pageNumber</param> and <param>pageSize</param><br>
	 * {@link Element}s are ordered by score ASC
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Element> getWorstRated(int pageNumber, int pageSize);

	/**
	 * return the best scored {@link Element}s 
	 * @return
	 */
	Element getWinner();
	
	/**
	 * Return a {@link Fight} of {@link Element}s 
	 * @return
	 */
	Fight getFight();
	
	/**
	 * Return the number of play
	 * @return
	 */
	int getTotalPlayed();
}
