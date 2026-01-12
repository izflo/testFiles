package io.ex;

import java.io.Serializable;

public class Score implements Serializable {

	private int kor;
	private int eng;
	private int math;

	public Score() {
	}

	public Score(int kor, int eng, int math) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "[kor=" + kor + ", eng=" + eng + ", math=" + math + "]";
	}
}
