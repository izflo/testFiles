package thread.ex;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LogThread extends Thread {
	// 문자열(nextLine())을 입력받을 때마다 inputstring.log파일에 날짜시간과 함께 출력
	// ex) [2024/11/24 09:00:00] 안녕하세요!

	// 날짜 포맷 생성 + 안녕하세요! -> 객체로 넘기기?

	private String text;
	private File file;
	private DataOutputStream dos;

	public LogThread() {
		this.file = new File("/Users/yeji/eclipse-workspace/코딩 테스트/filesinputstring.log");
		try {
			this.dos = new DataOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public LogThread(String text) {
		super();
		this.text = text;
	}

	public LogThread(String text, File file) {
		super();
		this.text = text;
		this.file = file;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void run() {

		Scanner sc = new Scanner(System.in);
//		File file = new File("D:/pub2511/files/inputstring.log");
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]"); // 날짜 포맷

		while (sc.hasNextLine()) {

			String dateStr = sdf.format(new Date()); // 생성된 날짜
			String text = sc.nextLine();

			try {

				dos.writeUTF(dateStr);
				dos.writeUTF(text);
//				dos.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			dos.close();
			sc.close();
			Thread.interrupted();
		} catch (IOException e2) {
			e2.printStackTrace();
			System.exit(0);
		}

	}

}
