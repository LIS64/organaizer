package organaizer.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import organaizer.Entity.Client;
import organaizer.Entity.Clients;
import organaizer.Exeption.ClientNotFoundExeption;

public class XMLOperation {
	private Clients clients = new Clients();
	private JAXBContext context;
	private File xmlFile;
	private int findIndex;

	// �������� ������������ XML ����� � ������������� ������ � ��������
	public void initXMLContext(File file) {
		try {
			context = JAXBContext.newInstance(Clients.class);
			clients = (Clients) context.createUnmarshaller().unmarshal(file);
		} catch (JAXBException e) {
			System.out.println("XML ���� ������������! �������� ���� � ���������� �����!");
			System.exit(0);
		}
	}

	// �������� �� ������������ ���������� ���� � �����
	public void checkXMLFile(String fileName) throws IOException {
		xmlFile = new File(fileName);

		if (!xmlFile.exists()) {
			System.out.println("��������� ����� �� �������! ��� ������ ����� ����!");
			try {
				xmlFile.createNewFile();
				context = JAXBContext.newInstance(Clients.class);
			} catch (IOException | JAXBException e) {
				System.out.println("������ ��� �������� �����! �������� ��� ���� �������!");
				throw new IOException();
			}
		} else {
			try {
				context = JAXBContext.newInstance(Clients.class);
			} catch (JAXBException e) {
			}
			if (xmlFile.length() > 100){
				initXMLContext(xmlFile);
			}
		}
	}

	// �������� ������� �� xml �����
	public void deleteClientWithXMLFile(Client client) {
		clients.getClient().remove(client);
		updateClientToXMLFile();
		System.out.println("������ ������� �������!");
	}

	// ���������� ������ �� �������� � xml �����
	public void updateClientToXMLFile() {
		try {
			context.createMarshaller().marshal(clients, xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	// ������� ������ ������� � xml ����
	public void insertClientToXMLFile(Client client) {
		clients.getClient().add(client);
		updateClientToXMLFile();
		System.out.println("������ ������� ���������!");

	}

	// ���������� ������ ����� �������������� �������
	public void updateClientInformation(Client client) {
		clients.getClient().set(findIndex, client);
		System.out.println("������ ������� ��������!");
	}

	public Clients getClients() {
		return clients;
	}


	public File getXmlFile() {
		return xmlFile;
	}

	/* ������ ��� ��������� ������� �� ���������� ����� */
	// �������� �� Id
	public Client getClientById(int id) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getId() == id) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� ��������� Id: " + id + ". ������ �� �������!");
	}

	// �������� �� ���
	public Client getClientByFullname(String fullname) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getFullname().toLowerCase().equals(fullname)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� ��������� ���: " + fullname + ". ������ �� �������!");
	}

	// �������� �� �����������
	public Client getClientByOrganization(String organization) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getOrganization().toLowerCase().equals(organization)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� �������� �����������: " + organization + ". ������ �� �������!");
	}

	// �������� �� ���������
	public Client getClientByPosition(String position) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getPosition().toLowerCase().equals(position)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� �������� ���������: " + position + ". ������ �� �������!");
	}

	// �������� �� ���������� ��������
	public Client getClientByMobile(String mobile) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getPhone().getMobile().equals(mobile)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� ��������� ���������� ��������: " + mobile + ". ������ �� �������!");
	}

	// �������� �� email
	public Client getClientByEmail(String email) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getEmail().toLowerCase().equals(email)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("�� ��������� Email: " + email + ". ������ �� �������!");
	}
}
