// PERSON테이블 한 행의 데이터 <-> Person클래스 한 객체에 저장****

package jdbc;

import java.io.Serializable;

public class Person implements Serializable {

	public static final long serialVersionUID = 354652684516851L;

	private int pid;
	private String pname;
	private int page;

	public Person() {
	}

	public Person(int pid, String pname, int page) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.page = page;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", page=" + page + "]";
	}

}
