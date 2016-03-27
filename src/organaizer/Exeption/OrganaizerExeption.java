package organaizer.Exeption;

public class OrganaizerExeption extends Exception {
	private static final long serialVersionUID = 1L;

	public OrganaizerExeption() {
		super();
	}
	public OrganaizerExeption(String message, Throwable cause) {
		super(message, cause);
	}
	public OrganaizerExeption(String message) {
		super(message);
	}
}
