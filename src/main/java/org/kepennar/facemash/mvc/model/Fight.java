package org.kepennar.facemash.mvc.model;

import org.kepennar.facemash.model.Element;

public class Fight {

	
	private Element element1;
	private Element element2;
	
	
	public Fight() { }
	
	public Fight(Element element1, Element element2) {
		super();
		this.element1 = element1;
		this.element2 = element2;
	}

	public Element getElement1() {
		return element1;
	}

	public void setElement1(Element element1) {
		this.element1 = element1;
	}

	public Element getElement2() {
		return element2;
	}

	public void setElement2(Element element2) {
		this.element2 = element2;
	}
	
	

}
