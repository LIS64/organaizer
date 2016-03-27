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

	// Проверка корректности XML файла и инициализация данных о клиентах
	public void initXMLContext(File file) {
		try {
			context = JAXBContext.newInstance(Clients.class);
			clients = (Clients) context.createUnmarshaller().unmarshal(file);
		} catch (JAXBException e) {
			System.out.println("XML файл некорректный! Исправте файл и попробуйте снова!");
			System.exit(0);
		}
	}

	// Проверка на правильность указанного пути к файлу
	public void checkXMLFile(String fileName) throws IOException {
		xmlFile = new File(fileName);

		if (!xmlFile.exists()) {
			System.out.println("Указаного файла не найдено! Был создан новый файл!");
			try {
				xmlFile.createNewFile();
				context = JAXBContext.newInstance(Clients.class);
			} catch (IOException | JAXBException e) {
				System.out.println("Ошибка при создании файла! Возможно нет прав доступа!");
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

	// Удаления клиента из xml файла
	public void deleteClientWithXMLFile(Client client) {
		clients.getClient().remove(client);
		updateClientToXMLFile();
		System.out.println("Запись успешно удалена!");
	}

	// Обновление данных по клиентам в xml файле
	public void updateClientToXMLFile() {
		try {
			context.createMarshaller().marshal(clients, xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	// Вставка нового клиента в xml файл
	public void insertClientToXMLFile(Client client) {
		clients.getClient().add(client);
		updateClientToXMLFile();
		System.out.println("Запись успешно добавлена!");

	}

	// Обновление данных после редактирования клиента
	public void updateClientInformation(Client client) {
		clients.getClient().set(findIndex, client);
		System.out.println("Данные успешно изменены!");
	}

	public Clients getClients() {
		return clients;
	}


	public File getXmlFile() {
		return xmlFile;
	}

	/* МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ КЛИЕНТА ПО КОНКРЕТНЫМ ПОЛЯМ */
	// Получить по Id
	public Client getClientById(int id) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getId() == id) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаному Id: " + id + ". Ничего не найдено!");
	}

	// Получить по ФИО
	public Client getClientByFullname(String fullname) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getFullname().toLowerCase().equals(fullname)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаному ФИО: " + fullname + ". Ничего не найдено!");
	}

	// Получить по организации
	public Client getClientByOrganization(String organization) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getOrganization().toLowerCase().equals(organization)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаной орагнизации: " + organization + ". Ничего не найдено!");
	}

	// Получить по должности
	public Client getClientByPosition(String position) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getPosition().toLowerCase().equals(position)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаной должности: " + position + ". Ничего не найдено!");
	}

	// Получить по мобильному телефону
	public Client getClientByMobile(String mobile) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getPhone().getMobile().equals(mobile)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаному мобильному телефону: " + mobile + ". Ничего не найдено!");
	}

	// Получить по email
	public Client getClientByEmail(String email) throws ClientNotFoundExeption {
		for (int i = 0; i < clients.getClient().size(); i++) {
			if (clients.getClient().get(i).getEmail().toLowerCase().equals(email)) {
				findIndex = i;
				return clients.getClient().get(i);
			}
		}
		throw new ClientNotFoundExeption("По указаному Email: " + email + ". Ничего не найдено!");
	}
}
