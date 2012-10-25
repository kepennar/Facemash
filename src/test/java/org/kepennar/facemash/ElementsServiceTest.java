package org.kepennar.facemash;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.service.ElementService;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ElementsServiceTest {

	@Inject
	@Named("elementService")
    private ElementService elementService;
	
	@Test
	public void paginationIsCorrect() {
		Page<Element> page = elementService.getTopRated(1, 3);
		
		assertEquals(page.getNumber(), 3);
	}
}
