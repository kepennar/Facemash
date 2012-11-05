package org.kepennar.facemash.util;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {

	private Broadcaster broadcaster;
	
	private Integer tolalPlayed;

	
	public Integer getTolalPlayed() {
		return tolalPlayed;
	}

	public void setTolalPlayed(Integer tolalPlayed) {
		this.tolalPlayed = tolalPlayed;
	}
	
	public synchronized void play() {
		if(tolalPlayed == null) {
			tolalPlayed = 0;
		}
		tolalPlayed++;
		if (broadcaster == null) {
			broadcaster = BroadcasterFactory.getDefault().lookup(Constantes.VOTES_BROADCASTER, true);
		}
		broadcaster.broadcast(tolalPlayed);
	}
}
