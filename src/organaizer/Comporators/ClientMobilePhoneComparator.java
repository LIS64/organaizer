package organaizer.Comporators;

import java.util.Comparator;

import organaizer.Entity.Client;

public class ClientMobilePhoneComparator implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		return o1.getPhone().getMobile().compareTo(o2.getPhone().getMobile());
	}

}
