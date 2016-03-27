package organaizer.Comporators;

import java.util.Comparator;

import organaizer.Entity.Client;

public class ClientFullnameComparator implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		return o1.getFullname().compareTo(o2.getFullname());
	}

}
