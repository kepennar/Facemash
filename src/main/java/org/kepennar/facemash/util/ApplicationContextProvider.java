package org.kepennar.facemash.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext pApplicationContext)
			throws BeansException {
		if (pApplicationContext == null) {
			throw new FatalBeanException("The parameter pApplicationContext should not be null");
		}
		applicationContext = pApplicationContext;
		
	}
	
	public static ApplicationContext get() {
		return applicationContext;
	}

}
