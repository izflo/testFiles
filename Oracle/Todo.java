package jdbc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Todo implements Serializable {

	private static final long serialVersionUID = 230182800928382885L;

	private long tdno;
	private Timestamp tddate;
	private String tdwriter;
	private String tdcontent;
	private String tdyn;

	public Todo() {
	}

	public Todo(long tdno, Timestamp tddate, String tdwriter, String tdcontent, String tdyn) {
		super();
		this.tdno = tdno;
		this.tddate = tddate;
		this.tdwriter = tdwriter;
		this.tdcontent = tdcontent;
		this.tdyn = tdyn;
	}

	public long getTdno() {
		return tdno;
	}

	public void setTdno(long tdno) {
		this.tdno = tdno;
	}

	public Timestamp getTddate() {
		return tddate;
	}

	public void setTddate(Timestamp tddate) {
		this.tddate = tddate;
	}

	public String getTdwriter() {
		return tdwriter;
	}

	public void setTdwriter(String tdwriter) {
		this.tdwriter = tdwriter;
	}

	public String getTdcontent() {
		return tdcontent;
	}

	public void setTdcontent(String tdcontent) {
		this.tdcontent = tdcontent;
	}

	public String getTdyn() {
		return tdyn;
	}

	public void setTdyn(String tdyn) {
		this.tdyn = tdyn;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		
		return "번호: " + tdno + ", 등록일시: " + sdf.format(tddate) + ", 작성자: " + tdwriter + ", 내용: " + tdcontent
				+ ", 완료여부: " + (tdyn.equals("Y") ? "완료":"미완료");
	}

}
