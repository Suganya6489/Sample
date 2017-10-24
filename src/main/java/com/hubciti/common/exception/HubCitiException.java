package com.hubciti.common.exception;

public class HubCitiException extends Exception {

	/**
	 * Error code declared as int
	 */
	private int errorCode;

	/**
	 * myException declared as Throwable object
	 */
	private Throwable myException;

	/**
	 * Constructer.
	 */
	public HubCitiException() {
		super();
	}

	/**
	 * Constructer.
	 * 
	 * @param errorMessage
	 *            String
	 * @param errorCause
	 *            Throwable
	 */
	public HubCitiException(String errorMessage, Throwable errorCause) {
		super(errorMessage, errorCause);
	}

	/**
	 * Constructer.
	 * 
	 * @param errorMessage
	 * @param errorCode
	 * @param errorCause
	 */
	public HubCitiException(String errorMessage, int errorCode, Throwable errorCause) {
		super(errorMessage, errorCause);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor.
	 * 
	 * @param errorMessage
	 *            String
	 */
	public HubCitiException(String errorMessage) {
		super(errorMessage);

	}

	/**
	 * Constructer.
	 * 
	 * @param errorCause
	 */
	public HubCitiException(Throwable errorCause) {
		super(errorCause);
	}

	/**
	 * To get error code.
	 * 
	 * @return errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * To set error code.
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * To get myException
	 * 
	 * @return
	 */
	public Throwable getMyException() {
		return myException;
	}

	/**
	 * To set myException
	 * 
	 * @param myException
	 */
	public void setMyException(Throwable myException) {
		this.myException = myException;
	}

}
