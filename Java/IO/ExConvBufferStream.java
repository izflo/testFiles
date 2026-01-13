package io.ex;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class ExConvBufferStream {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Writer fw = null;
		Reader fr = null;

		File file = new File("D:/pub2511/files/scanner1.txt");

		try {
			fw = new FileWriter(file);
			fw.write(scanner.nextLine());
			fw.flush();

			fr = new FileReader(file);
			int i = 0;
			while ((i = fr.read()) > -1) {
				System.out.print((char) i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();
				scanner.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}// main

}// class
