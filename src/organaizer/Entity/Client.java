package organaizer.Entity;


import javax.xml.bind.annotation.XmlType;

@XmlType(name="client")
public class Client {
	private int id;
	private String fullname;
	private String position;
	private String organization;
	private String email;
	private Phone phone;
	
	public Client(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		String result = "id=" + id + ", fullname=" + fullname + ", position=" + position + ", organization="
				+ organization + ", email=" + email + ", "
				+ "\nPhones: Mobile:" + phone.getMobile() + "; Home: "+phone.getHome()+"; Work: "+phone.getWork()+".";
		return result;
	}
	
	



}
