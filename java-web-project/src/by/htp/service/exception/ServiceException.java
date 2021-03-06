package by.htp.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -4258507510597455029L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
