package br.com.via.exception;

public class ProductResponseException {

	private String status;
	private String message;

	public ProductResponseException(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ProductResponseException [status=" + status + ", message=" + message + "]";
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
