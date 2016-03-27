package organaizer.Exeption;

public class CancelOperationExeption extends OrganaizerExeption {
	private static final long serialVersionUID = 1L;

	public CancelOperationExeption() {
		super();
	}
	public CancelOperationExeption(String message, Throwable cause) {
		super(message, cause);
	}
	public CancelOperationExeption(String message) {
		super(message);
	}
}
