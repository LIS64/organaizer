package organaizer.Comporators;

import java.util.Comparator;

import organaizer.Entity.Client;

public class ClientOrganizationComparator implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		return o1.getOrganization().compareTo(o2.getOrganization());
	}

}
