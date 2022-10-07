package tuto.prog_principles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFiles {

	public static void main(String[] args) {
		// ----------- READ ----------- //
		try {
			// path from the project root
			// or absolute path "E:/Java/workspace/java_03_prog_principles_tuto/test.txt"
			FileReader fileReader = new FileReader("test.txt");
			BufferedReader reader = new BufferedReader(fileReader);

			try {
				String line = reader.readLine();

				while (line != null) {
					System.out.println(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("oupsy! WTF is that exception?");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("oupsy! File not found...");
		}

		// ----------- WRITE ----------- //
		try {
			FileWriter fileWriter = new FileWriter("test.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			writer.newLine();
			writer.write("YOLOOOOOO");
			writer.newLine();
			writer.write("HIHI HAHAH HAHAHAHAHA");

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("oupsy! File not found...");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("oupsy! WTF is that exception?");
		}
	}

}
