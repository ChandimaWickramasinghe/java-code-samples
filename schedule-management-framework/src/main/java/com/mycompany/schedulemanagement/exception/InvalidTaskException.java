package com.mycompany.schedulemanagement.exception;

/**
 * InvalidTaskException is the wrapper of Exception class used in schedule
 * management.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class InvalidTaskException extends Exception {

	private static final long serialVersionUID = -3001080477603535001L;

	/**
	 * Constructor with exception message
	 * 
	 * @param msg
	 *            Exception message
	 */
	public InvalidTaskException(String msg) {
		super(msg);
	}

	/**
	 * Constructor with exception message and original exception
	 * 
	 * @param msg
	 *            exception message
	 * @param e
	 *            original exception occurred
	 */
	public InvalidTaskException(String msg, Exception e) {
		super(msg, e);
	}

}
