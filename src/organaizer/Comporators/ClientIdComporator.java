package organaizer.Comporators;

import java.util.Comparator;

import organaizer.Entity.Client;

public class ClientIdComporator implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		return	o1.getId() > o2.getId() ? 1 : -1;
	}

}
