package organaizer;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import organaizer.Comporators.ClientEmailComparator;
import organaizer.Comporators.ClientFullnameComparator;
import organaizer.Comporators.ClientIdComporator;
import organaizer.Comporators.ClientMobilePhoneComparator;
import organaizer.Comporators.ClientOrganizationComparator;
import organaizer.Comporators.ClientPositionComparator;
import organaizer.Entity.Client;
import organaizer.Entity.Phone;
import organaizer.Exeption.CancelOperationExeption;
import organaizer.Exeption.ClientNotFoundExeption;
import organaizer.Exeption.OrganaizerExeption;
import organaizer.xml.XMLOperation;

public class Operations {

	//Данные клиентов из файла XML
	public static final String ID = "id";
	public static final String FULL_NAME = "fullname";
	public static final String EMAIL = "email";
	public static final String MOBILE_PHONE = "mobile";
	public static final String HOME_PHONE = "home";
	public static final String WORK_PHONE = "work";
	public static final String ORGANIZATION = "organization";
	public static final String POSITION = "position";
	public static final String OTHER = "other";

	
	//Служебные комманды органайзера
	public static final String CANCEL = "cancel";
	public static final String COMMIT = "commit";
	
	//Служебные сообщения органайзера
	public static final String OPERATION_CANCEL = "Ваша операция была отменена";
	public static final String MSG_CANCEL = "Для отмены операции введите CANCEL";
	public static final String MSG_ERROR_PARAMS = "Вы ввели неверный параметр ";
	public static final String MSG_FIND = "Для поиска клиента введите данные о нём"
			                           	+ "в формате имя_поля=значение. "
			                           	+ "Допустимые поля: id, fullname, organization, "
			                           	+ "email, position. Поле для поиска по телефону: mobile"
				                        + "\nК примеру: id=1, fullname=Petrov";
	public static final String MSG_DELETE =  "Для удаления клиента введите данные о нём"
										  + "в формате имя_поля=значение. "
										  + "Допустимые поля: id, fullname, organization, "
                                          + "email, position. Поле для поиска по телефону: mobile"
                                          + "\nК примеру: id=1, fullname=Petrov";
	public static final String MSG_UPDATE = "Для редактирования полей, используйте формат имя_поля=новое_значение"
			                              + "\nдля подтверждения изменений введите commit";
	

	private Scanner scanner;
	//Обработчик операций связанных с XML файлом
	private XMLOperation xml = new XMLOperation();
	private boolean isRead;

	public Operations(Scanner scanner) {
		this.scanner = scanner;
	}

	public void list(String command) {
		try {
			//Проверяем валидность введенных параметров и сортируем по указаным параметрам
			checkAndSortParams(command);
		} catch (CancelOperationExeption e) {
			System.out.println(OPERATION_CANCEL);
		}
	}

	
	public void find() {
		isRead = true;
		System.out.println(MSG_CANCEL + "\n"+ MSG_FIND);
		try {
			//Проверяем валидность введенных параметров поиска и существует ли такой клиент
			Client client = checkFindParameters();
			System.out.println(client);
		} catch (CancelOperationExeption e) {
			System.out.println(OPERATION_CANCEL);
		} finally {
			isRead = false;
		}
	}

	public void delete() {
		isRead = true;
		System.out.println(MSG_CANCEL + "\n"+ MSG_DELETE);
		try {
			//Проверяем валидность введенных параметров для удаления и существует ли такой клиент
			Client client = checkFindParameters();
			xml.deleteClientWithXMLFile(client);
		} catch (CancelOperationExeption e) {
			System.out.println(OPERATION_CANCEL);
		} finally {
		isRead = false;
		}
	}

	public void update() {
		System.out.println(MSG_CANCEL + "\n"+ MSG_FIND);
		try {
			isRead = true;
			//Проверяем валидность введенных параметров и существует ли такой клиент
			Client client = checkFindParameters();
			System.out.println(client + "\n\n" + MSG_UPDATE);
			//Проверяем валидность введенных параметров для редактирования клиента
			checkUpdateParameters(client);
			//Обновляем информацию по клиенту в XML файле
			xml.updateClientInformation(client);

		} catch (OrganaizerExeption e) {
			System.out.println(OPERATION_CANCEL);
		} finally {
			isRead = false;
		}

	}

	public void insert() {
		System.out.println(MSG_CANCEL);
		Client client = new Client();
		try {
			//Проверяем корректность введеного номера клиента
			System.out.println("Введите номер клиента: ");
			System.out.print("id=");
			String idValue = scanner.nextLine().replace("id=", "");
			client.setId(Integer.valueOf(checkValue(idValue, ID)));

			//Проверяем корректность введеного ФИО клиента
			System.out.println("Введите ФИО клиента. Формат ввода: Иванов Иван Иванович");
			System.out.print("fullname=");
			client.setFullname(checkValue(scanner.nextLine().replace("fullname=", ""), FULL_NAME));

			//Проверяем корректность введеной должности клиента
			System.out.println("Введите должность клиента: ");
			System.out.print("position=");
			client.setPosition(checkValue(scanner.nextLine().replace("position=", ""), OTHER));

			//Вводим органиацию клиента
			System.out.println("Введите организацию клиента: ");
			System.out.print("organization=");
			client.setOrganization(scanner.nextLine().replace("organization=", ""));

			//Проверяем корректность введеного email клиента
			System.out.println("Введите email клиента. Формат ввода: example@mail.ru ");
			System.out.print("email=");
			client.setEmail(checkValue(scanner.nextLine().replace("email=", ""), EMAIL));
			
			//Проверяем корректность введеных телефоно клиента
			System.out.println("Заполните телефоны клиента, мобильный телефон обязательный к заполнению! "
					         + "\nОсальные опоционально!");
			System.out.println("Введите мобильный телефон клиента. Формат ввода: 8 900 000 00 00");
			System.out.print("mobile=");
			Phone phone = new Phone();
			phone.setMobile(checkValue(scanner.nextLine().replace("mobile=", ""), MOBILE_PHONE));
			System.out.println("Введите домашний телефон клиента. Формат ввода: 555 555. Или оставте поле пустым");
			System.out.print("home=");
			phone.setHome(checkValue(scanner.nextLine().replace("home=", ""), HOME_PHONE));
			System.out.println("Введите рабочий телефон клиента. Формат ввода: 55 55 55. Или оставте поле пустым");
			System.out.print("work=");
			phone.setWork(checkValue(scanner.nextLine().replace("work=", ""), WORK_PHONE));
			client.setPhone(phone);
			
			//Вставляем нового клиента в xml файл
			xml.insertClientToXMLFile(client);

		} catch (CancelOperationExeption e) {
			System.out.println(OPERATION_CANCEL);
		}

	}

	//Метод для проверки параметров и выполнения сортировки клиентов
	public void checkAndSortParams(String commands) throws CancelOperationExeption {
		String[] params = null;
		String[] command = commands.split(" ");
		if (command.length < 2) {
			System.out.println(xml.getClients());
		} else {
			params = command[1].split(";");
			for (int i = 0; i < params.length; i++) {
				switch (params[i].trim().toLowerCase()) {
				case ID:
					Collections.sort(xml.getClients().getClient(), new ClientIdComporator());
					continue;
				case FULL_NAME:
					Collections.sort(xml.getClients().getClient(), new ClientFullnameComparator());
					continue;
				case ORGANIZATION:
					Collections.sort(xml.getClients().getClient(), new ClientOrganizationComparator());
					continue;
				case POSITION:
					Collections.sort(xml.getClients().getClient(), new ClientPositionComparator());
					continue;
				case EMAIL:
					Collections.sort(xml.getClients().getClient(), new ClientEmailComparator());
					continue;
				case MOBILE_PHONE:
					Collections.sort(xml.getClients().getClient(), new ClientMobilePhoneComparator());
					continue;
				default:
					System.out.println(MSG_ERROR_PARAMS + params[i]);
					throw new CancelOperationExeption();
				}
			}
			System.out.println(xml.getClients());
		}
	}
	
	//Метод для проверки параметров поиска клиента
	private Client checkFindParameters() throws CancelOperationExeption {
		String[] params;
		boolean isStop = false;
		while (!isStop) {
			params = scanner.nextLine().split("=");
			if (params[0].trim().toLowerCase().equals(CANCEL)) {
				throw new CancelOperationExeption();
			}
			if (params.length < 2 || params[1] == "") {
				System.out.println(MSG_ERROR_PARAMS);
				continue;
			}
			try {
				switch (params[0].trim().toLowerCase()) {
				case ID:
					return xml.getClientById(Integer.valueOf(checkValue(params[1].trim(), ID)));
				case FULL_NAME:
					return xml.getClientByFullname(checkValue(params[1].trim(), FULL_NAME));
				case ORGANIZATION:
					return xml.getClientByOrganization(checkValue(params[1].trim(), ORGANIZATION));
				case POSITION:
					return xml.getClientByPosition(checkValue(params[1].trim(), POSITION));
				case EMAIL:
					return xml.getClientByEmail(checkValue(params[1].trim(), EMAIL));
				case MOBILE_PHONE:
					return xml.getClientByMobile(checkValue(params[1].trim(), MOBILE_PHONE));
				default:
					System.out.println(MSG_ERROR_PARAMS+params[0]);
					continue;
				}
			} catch (ClientNotFoundExeption e) {
				System.out.println(e);
				continue;
			}
		}
		return null;
	}

	//Метод для проверки параметров редактирования клиента
	public Client checkUpdateParameters(Client client) throws CancelOperationExeption {
		String[] params;
		boolean isStop = false;
		while (!isStop) {
			params = scanner.nextLine().split("=");
			if (params[0].trim().toLowerCase().equals(COMMIT)) {
				break;
			}
			if (params[0].trim().toLowerCase().equals(CANCEL)) {
				throw new CancelOperationExeption();
			}
			if (params.length < 2 || params[1] == "") {
				System.out.println(MSG_ERROR_PARAMS);
				continue;
			}
			switch (params[0].trim().toLowerCase()) {
			case ID:
				client.setId(Integer.valueOf(checkValue(params[1].trim(), ID)));
				System.out.println("Поле Id принято к изменению");
				continue;
			case FULL_NAME:
				client.setFullname(checkValue(params[1].trim(), FULL_NAME));
				System.out.println("Поле ФИО принято к изменению");
				continue;
			case ORGANIZATION:
				client.setOrganization(params[1].trim());
				System.out.println("Поле организация принято к изменению");
				continue;
			case POSITION:
				client.setPosition(params[1].trim());
				System.out.println("Поле должность принято к изменению");
				continue;
			case EMAIL:
				client.setEmail(checkValue(params[1].trim(), EMAIL));
				System.out.println("Поле email принято к изменению");
				continue;
			case MOBILE_PHONE:
				client.getPhone().setMobile(checkValue(params[1].trim(), MOBILE_PHONE));
				System.out.println("Поле мобильный телефон принято к изменению");
				continue;
			case HOME_PHONE:
				client.getPhone().setHome(checkValue(params[1].trim(), HOME_PHONE));
				System.out.println("Поле домашний телефон принято к изменению");
				continue;
			case WORK_PHONE:
				client.getPhone().setWork(checkValue(params[1].trim(), WORK_PHONE));
				System.out.println("Поле рабочий телефон принято к изменению");
				continue;
			default:
				System.out.println(MSG_ERROR_PARAMS+params[0]);
				continue;
			}
		}
		return client;
	}

	//Метод делегирует проверку правильности xml файла, классу для работы с xml файлом
	public void checkFile(String file) throws IOException {
		xml.checkXMLFile(file);
	}
	//Общий метод проверки введенных значений
		private String checkValue(String value, String type) throws CancelOperationExeption {
			boolean stop = false;
			boolean isValidate;
			value = value.toLowerCase();
			while (!stop) {
				if (CANCEL.equals(value)) {
					throw new CancelOperationExeption();
				} else if (ID.equals(type)) {
					try {
						Integer.valueOf(value);
						try {
							if (xml.getXmlFile().length() > 100){
								xml.getClientById(Integer.valueOf(value));
								if (isRead) {
									stop = true;
								} else {
									System.out.println("По указаному id уже существует клиент, введите другой id");
								    System.out.print("id=");
								    value = scanner.nextLine().replace("id=", "");	
								}
							} else {
								stop = true;
							}

						} catch (ClientNotFoundExeption e) {
							if (!isRead) {
								stop = true;
							} else {
								System.out.println("По указаному id не существует клиента, введите другой id");
							    System.out.print("id=");
							    value = scanner.nextLine().replace("id=", "");
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("Выввели неверное значение, пожалуйста введите цифру!");
					    System.out.print("id=");
					    value = scanner.nextLine().replace("id=", "");
					}
				}
				else if (FULL_NAME.equals(type)) {
					isValidate = value.matches("\\D+\\s?\\D+\\s?\\D+");
					if (!isValidate) {
						System.out.println("Неверный формат ФИО! Пример: Иванов Иван Иванович");
						System.out.print("fullname=");
						value = scanner.nextLine().replace("fullname=", "");
					} else {
						stop = true;
					}
				}
				else if (EMAIL.equals(type)) {
					isValidate = value.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
					if (!isValidate) {
						System.out.println("Выввели неверно email попробуйте снова в формате example@mail.ru");
						System.out.print("email=");
						value = scanner.nextLine().replace("email=", "");
					} else {
						stop = true;
					}
				}
				else if (MOBILE_PHONE.equals(type)) {
					isValidate = value.matches("\\d?\\s?\\d{3}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}");
					if (!isValidate) {
						System.out.println("Неверный формат мобильного телефона! Пример: 8 900 000 00 00");
						System.out.print("mobile=");
						value = scanner.nextLine().replace("mobile=", "");
					} else {
						stop = true;
					}
				}
				else if (HOME_PHONE.equals(type)) {
					if (value.equals("")) {
						stop = true;
					} else {
						isValidate = value.matches("\\d{3}\\s\\d{3}");
						if (!isValidate) {
							System.out.println("Неверный формат домашнего телефона! Пример: 555 555");
							System.out.print("home=");
							value = scanner.nextLine().replace("home=", "");
						} else {
							stop = true;
						}
					}
				} else if (WORK_PHONE.equals(type)) {
					if (value.equals("")) {
						stop = true;
					} else {
						isValidate = value.matches("\\d{2}\\s\\d{2}\\s\\d{2}");
						if (!isValidate) {
							System.out.println("Неверный формат рабочего телефона! Пример: 55 55 55");
							System.out.print("work=");
							value = scanner.nextLine().replace("work=", "");
						} else {
							stop = true;
						}
					}
				}
				else if (OTHER.equals(type)) {
					stop = true;
				}
			}
			return value;
		}
}
