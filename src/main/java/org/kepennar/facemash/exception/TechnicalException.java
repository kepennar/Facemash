package org.kepennar.facemash.exception;



/**
 * Technical exception
 *	//TODO Make something ( @link{org.springframework.web.servlet.HandlerInterceptor}) form ma naging this exception
 * @author KEPENNAR
 *
 */
public class TechnicalException extends RuntimeException {
	private static final long serialVersionUID = -5178733807402975161L;
	
	public TechnicalException(final Throwable pCause) {
		super(pCause);
	}
	
	public TechnicalException(final String pMessage) {
		super(pMessage);
	}
	
	public TechnicalException(final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	}
}
