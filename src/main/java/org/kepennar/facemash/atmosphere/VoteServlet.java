package org.kepennar.facemash.atmosphere;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.atmosphere.config.service.MeteorService;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.HeaderConfig;
import org.atmosphere.cpr.Meteor;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.websocket.WebSocketEventListenerAdapter;

@SuppressWarnings("serial")
@MeteorService(path = "/realtime/votes",
interceptors = {
        AtmosphereResourceLifecycleInterceptor.class})
public class VoteServlet extends HttpServlet {

	 @Override
	    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        // Create a Meteor
	        Meteor meteor = Meteor.build(req);
	        // Log all events on the console, including WebSocket events.
	        meteor.addListener(new WebSocketEventListenerAdapter());
	        
	        res.setContentType("text/html;charset=ISO-8859-1");
	        Broadcaster broadcaster = lookupBroadcaster(req.getPathInfo());
	       
	        meteor.setBroadcaster(broadcaster);

	        String header = req.getHeader(HeaderConfig.X_ATMOSPHERE_TRANSPORT);
	        if (header != null && header.equalsIgnoreCase(HeaderConfig.LONG_POLLING_TRANSPORT)) {
	            req.setAttribute(ApplicationConfig.RESUME_ON_BROADCAST, Boolean.TRUE);
	            meteor.suspend(-1, false);
	        } else {
	            meteor.suspend(-1);
	        }
	    }

	    private Broadcaster lookupBroadcaster(String pathInfo) {
	        String[] decodedPath = pathInfo.split("/");
	        return BroadcasterFactory.getDefault().lookup(decodedPath[decodedPath.length - 1], true);
	    }

}
