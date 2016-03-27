package organaizer;

import java.io.IOException;
import java.util.Scanner;




public class Oraganaizer {

	//Команды органайзера
	public static final String HELP = "help";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String BREAK = "break";
	public static final String LIST = "list";
	public static final String INSERT = "insert";
	public static final String FIND = "find";
	
	public static final String MSG_HELP = "Вы воспользовались справкой по программе Органайзер"
			                           + "\nДанная программа может быть использована для следующих команд: "
			                           + "\n\nHELP - получение справки по командам программы."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nINSERT - добавление нового клиента. Формат использования команды:"
			                           + "\nвведите команду insert, и затем последовательно заполняете данные по клиенту"
			                           + " по указанию программы."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nUPDATE - позволяет редактировать данные клиента. Формат использования команды:"
			                           + "\nвведите команду update и затем вы должны указать один из 6 параметров для поиска"
			                           + "\nклиента в XML файле, в формате: имя_параметра=значение. Доступные параметры:"
			                           + "\nid, fullname, organization, email, position. "
			                           + "Поле для поиска по телефону: mobile. К примеру id=1. После того как клиент"
			                           + "\nпо указаному параметру был найден, вы можете менять его поля по такому же принципу"
			                           + "\nимя_поля=значение, для редактирования доступны следующие поля: "
			                           + "\nid,fullname, organization, email, position. Так же для редактирования телефонов: "
			                           + "\nmobile, home, work. После завершения редактирования полей необходимо"
			                           + "\nподствердить действие командой COMMIT после чего, все изменения клиента"
			                           + "\nбудут записаны в XML файл."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nDELETE - удаление клиента. Формат использования команды:"
			                           + "введите команду delete, и затем вы должны указать один из 6 параметров для поиска"
			                           + "\nи затем удаления нужного клиента из XML файла, в формате: имя_параметра=значение."
			                           + "\nДоступные параметры: id, fullname, organization, email, position. "
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nLIST fieldName1;fieldName2;....;fieldNameN - отображение всех текущих клиентов"
			                           + "\nФормат использования команды: введите команду list и укажите по желанию нужные поля для сортировки."
			                           + "\nпараметры fieldName не обязательны, если их не указать то будет выведен весь список"
			                           + "\nклиентов без сортировки. Если будут указаны соответствующие поля, то будет произведена"
			                           + "\nсортировка клиента по этим полям. Доступные поля: id, fullname, organization,"
			                           + " email, position. Так же для сортировки по телефону поле: mobile."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nFIND - поиск клиента по указаным полям. Формат использования команды:"
			                           + "\nвведите команду find, затем укажите имя_поля=значение по которому будет производиться поиск"
			                           + "\nДоступные поля: id, fullname, organization, email, position. "
			                           + "\nТак же для сортировки по телефону поле: mobile.";
	public static final String MSG_WELCOME = "Добро пожаловать в консольный Органайзер который позволяет"
			                               + " обрабатывать данные по клиентам. \nДля получения справки "
			                               + "воспользуйтесь командой help";
	public static final String MSG_ERROR_COMMAND = "Вы ввели несуществующую команду! Воспользуйтесь командой help";
	//Путь к XML файлу с данными
	public static final String XML_FILE = "clients.xml";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println(MSG_WELCOME);
		//Создаём обраотчика для всех операций органайзера
		Operations operation = new Operations(scanner);
		//Проверяем правильность XML файла
		try {
			operation.checkFile(XML_FILE);
		} catch (IOException e) {
			System.exit(0);;
		}
		String inCommand;
		boolean isExit = false;
		while (!isExit) {
			inCommand = scanner.nextLine().trim().toLowerCase();
			if (inCommand.contains(BREAK)) {
				scanner.close();
				isExit = true;
			}
			else if (inCommand.equals(HELP)){
				System.out.println(MSG_HELP);
			}

			else if (inCommand.contains(LIST)) {
				operation.list(inCommand);
			} 
			else if (inCommand.equals(INSERT)) {
				operation.insert();
			}
			else if (inCommand.equals(DELETE)){
				operation.delete();
			}
			else if (inCommand.equals(UPDATE)){
				operation.update();
			}
			else if (inCommand.equals(FIND)){
				operation.find();
			}
			else {
				System.out.println(MSG_ERROR_COMMAND);
			}
			
		}

	}



}
