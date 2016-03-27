package organaizer.Exeption;

public class ClientNotFoundExeption extends OrganaizerExeption {
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundExeption() {
		super();
	}
	public ClientNotFoundExeption(String message, Throwable cause) {
		super(message, cause);
	}
	public ClientNotFoundExeption(String message) {
		super(message);
	}
}
