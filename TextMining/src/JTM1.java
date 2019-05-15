import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.InputMismatchException;
import java.util.Scanner;

public class JTM1 {

	public static void main(String[] argStrings) {

		try {
			Scanner scanner = new Scanner(System.in);

			String[] dataStrings = null;
			String isExit = "n";
			while (isExit.equals("n")) {
				System.out.println("1. Text importing;\n2. Text ");
				int option = scanner.nextInt();
				System.out.println("option: " + option);
				switch (option) {
				case 1:
					System.out.println("enter the file directory:\n");
					String directoryString = scanner.nextLine();
					directoryString = scanner.nextLine();
				System.out.println("directory:"+directoryString);
					File dirFile = new File(directoryString);
					File[] files = dirFile.listFiles();
					dataStrings = new String[files.length];
					for (int i = 0; i < files.length; i++) {
						BufferedReader bReader = new BufferedReader(new FileReader(files[i]));
						String conteString = "";
						String lineString = bReader.readLine();
						while (lineString != null) {
							conteString += lineString;
							lineString = bReader.readLine();
						}
						bReader.close();
						dataStrings[i] = conteString;
						System.out.println(conteString);
					}
					break;
				case 2:

					break;

				case 3:

					break;

				case 4:

					break;
				default:
					break;
				}
				System.out.println("do you want to exit the program?(y/n) ");
				isExit = scanner.next();
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("program exited!");
		}

	}
}
