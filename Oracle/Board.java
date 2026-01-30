package jdbc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Board implements Serializable {

	private static final long serialVersionUID = 87203168887897394L;

	private int bno;
	private Timestamp bdate;
	private String bwriter;
	private String btitle;
	private String bcontent;
	private String bfname;
	private int bviews;

	public Board() {
	}

	public Board(int bno, Timestamp bdate, String bwriter, String btitle, String bcontent, String bfname, int bviews) {
		super();
		this.bno = bno;
		this.bdate = bdate;
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfname = bfname;
		this.bviews = bviews;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public Timestamp getBdate() {
		return bdate;
	}

	public void setBdate(Timestamp bdate) {
		this.bdate = bdate;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBfname() {
		return bfname;
	}

	public void setBfname(String bfname) {
		this.bfname = bfname;
	}

	public int getBviews() {
		return bviews;
	}

	public void setBviews(int bviews) {
		this.bviews = bviews;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");

		return "번호: " + bno + ", 작성일시: " + sdf.format(bdate) + ", 작성자: " + bwriter + ", 제목: " + btitle + ", 내용: "
				+ bcontent + ", 첨부파일명: " + bfname + ", 조회수: " + bviews;
	}

}
