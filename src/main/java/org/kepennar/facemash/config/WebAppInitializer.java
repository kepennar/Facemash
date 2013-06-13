package org.kepennar.facemash.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.atmosphere.cpr.MeteorServlet;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	    	
    	setSpringMvcContext(servletContext);
    	setSpringSecurityContext(servletContext); 
    	initializeAtmosphere(servletContext);
    
    }
    
    private void setSpringMvcContext(ServletContext servletContext) {
    	XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        String[] locations = { "classpath*:applicationContext.xml" };
        appContext.setConfigLocations(locations);
                
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
        
        servletContext.addFilter("etagFilter", new ShallowEtagHeaderFilter());
                
        servletContext.addListener(new ContextLoaderListener(appContext));
    }
    
    
    private void setSpringSecurityContext(ServletContext servletContext) {
    	servletContext.addListener(new HttpSessionEventPublisher());
        
        FilterRegistration filterRegistration = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
    
    
    private void initializeAtmosphere(ServletContext servletContext) {
    	ServletRegistration.Dynamic meteor = servletContext.addServlet("atmosphereServlet", new MeteorServlet());
        
        meteor.setAsyncSupported(true);
        meteor.addMapping("/realtime/*");
        meteor.setLoadOnStartup(3);

        meteor.setInitParameter(
        		"org.atmosphere.servlet",
                "org.kepennar.facemash.atmosphere.VoteServlet");
        meteor.setInitParameter(
        		"org.atmosphere.cpr.broadcasterCacheClass",
                "org.atmosphere.cache.HeaderBroadcasterCache");
        meteor.setInitParameter(
        		"org.atmosphere.cpr.broadcastFilterClasses",
                "org.atmosphere.client.TrackMessageSizeFilter");
        meteor.setInitParameter("org.atmosphere.useNative", "true");
    }
}
