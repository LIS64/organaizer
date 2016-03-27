package organaizer.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clients")
public class Clients {

	private List<Client> client = new ArrayList<>();

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

	@Override
	public String toString() {
		String result = "----------------------CLIENT LIST----------------------------------\n"
			          + "id   fullname     organization     email      position   \n";
		for (Client cl : client) {
			result += "-------------------------------------------------------------------\n" + "" + cl.getId() + "  "
					+ cl.getFullname() + "  " + cl.getPosition() + "  " + cl.getOrganization() + "   \n"
					+ "Phones: Mobile: " + cl.getPhone().getMobile() + "; Home: " + cl.getPhone().getHome() + "; Work: "
					+ cl.getPhone().getWork() + ".\n";
		}
			  result += "-----------------------------END LIST------------------------------";
		return result;
	}

}
