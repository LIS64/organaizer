package organaizer;

import java.io.IOException;
import java.util.Scanner;




public class Oraganaizer {

	//������� �����������
	public static final String HELP = "help";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String BREAK = "break";
	public static final String LIST = "list";
	public static final String INSERT = "insert";
	public static final String FIND = "find";
	
	public static final String MSG_HELP = "�� ��������������� �������� �� ��������� ����������"
			                           + "\n������ ��������� ����� ���� ������������ ��� ��������� ������: "
			                           + "\n\nHELP - ��������� ������� �� �������� ���������."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nINSERT - ���������� ������ �������. ������ ������������� �������:"
			                           + "\n������� ������� insert, � ����� ��������������� ���������� ������ �� �������"
			                           + " �� �������� ���������."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nUPDATE - ��������� ������������� ������ �������. ������ ������������� �������:"
			                           + "\n������� ������� update � ����� �� ������ ������� ���� �� 6 ���������� ��� ������"
			                           + "\n������� � XML �����, � �������: ���_���������=��������. ��������� ���������:"
			                           + "\nid, fullname, organization, email, position. "
			                           + "���� ��� ������ �� ��������: mobile. � ������� id=1. ����� ���� ��� ������"
			                           + "\n�� ��������� ��������� ��� ������, �� ������ ������ ��� ���� �� ������ �� ��������"
			                           + "\n���_����=��������, ��� �������������� �������� ��������� ����: "
			                           + "\nid,fullname, organization, email, position. ��� �� ��� �������������� ���������: "
			                           + "\nmobile, home, work. ����� ���������� �������������� ����� ����������"
			                           + "\n������������ �������� �������� COMMIT ����� ����, ��� ��������� �������"
			                           + "\n����� �������� � XML ����."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nDELETE - �������� �������. ������ ������������� �������:"
			                           + "������� ������� delete, � ����� �� ������ ������� ���� �� 6 ���������� ��� ������"
			                           + "\n� ����� �������� ������� ������� �� XML �����, � �������: ���_���������=��������."
			                           + "\n��������� ���������: id, fullname, organization, email, position. "
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nLIST fieldName1;fieldName2;....;fieldNameN - ����������� ���� ������� ��������"
			                           + "\n������ ������������� �������: ������� ������� list � ������� �� ������� ������ ���� ��� ����������."
			                           + "\n��������� fieldName �� �����������, ���� �� �� ������� �� ����� ������� ���� ������"
			                           + "\n�������� ��� ����������. ���� ����� ������� ��������������� ����, �� ����� �����������"
			                           + "\n���������� ������� �� ���� �����. ��������� ����: id, fullname, organization,"
			                           + " email, position. ��� �� ��� ���������� �� �������� ����: mobile."
			                           + "\n-------------------------------------------------------------------------"
			                           + "\nFIND - ����� ������� �� �������� �����. ������ ������������� �������:"
			                           + "\n������� ������� find, ����� ������� ���_����=�������� �� �������� ����� ������������� �����"
			                           + "\n��������� ����: id, fullname, organization, email, position. "
			                           + "\n��� �� ��� ���������� �� �������� ����: mobile.";
	public static final String MSG_WELCOME = "����� ���������� � ���������� ���������� ������� ���������"
			                               + " ������������ ������ �� ��������. \n��� ��������� ������� "
			                               + "�������������� �������� help";
	public static final String MSG_ERROR_COMMAND = "�� ����� �������������� �������! �������������� �������� help";
	//���� � XML ����� � �������
	public static final String XML_FILE = "clients.xml";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println(MSG_WELCOME);
		//������ ���������� ��� ���� �������� �����������
		Operations operation = new Operations(scanner);
		//��������� ������������ XML �����
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
