package organaizer.Comporators;

import java.util.Comparator;

import organaizer.Entity.Client;

public class ClientEmailComparator implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		return o1.getEmail().compareTo(o2.getEmail());
	}

}
