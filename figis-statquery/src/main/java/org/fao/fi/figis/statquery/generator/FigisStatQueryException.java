package org.fao.fi.figis.statquery.generator;

/**
 * Generic FigisStatQueryException runtime exception. FigisStatQueryException so
 * far does not have checked exceptions, the idea behind is that checked
 * exceptions create a lot of ugly boilerplate code, and more important, all
 * code should be tested so that exception will not occur runtime.
 * 
 * 
 * 
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class FigisStatQueryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943961598799007990L;

	public FigisStatQueryException(Exception e) {
		super(e);
	}

	public FigisStatQueryException(String message) {
		super(message);
	}

}
