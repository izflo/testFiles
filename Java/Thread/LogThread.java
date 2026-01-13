package thread.ex;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogThread extends Thread {
	// 문자열(nextLine())을 입력받을 때마다 inputstring.log파일에 날짜시간과 함께 출력
	// ex) [2024/11/24 09:00:00] 안녕하세요!

	// 날짜 포맷 생성 + 안녕하세요! -> 객체로 넘기기?
	// long endTime = System.currentTimeMillis();

	private String text;
	private File file;
	private DataOutputStream dos = null;

	public LogThread() {
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]"); // 날짜 포맷
		String dateStr = sdf.format(new Date()); // 생성된 날짜
		String logText = text; // 입력받은 문자열
		
		try {
			dos= new DataOutputStream(new FileOutputStream(file));
			
			dos.writeUTF(dateStr);
			dos.writeUTF(logText);
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

		
		
		

	}

}
