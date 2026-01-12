package io.ex;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 923847293844829839L;
	private transient String ssn; // ssn이 어떤 값을 가지던지 String타입의 기본값인 null로 직렬화 됨
	private String name;
	private Score score;

	public Student() {
	}

	public Student(String name) {
		super();
		this.name = name;
	}

	public Student(String name, Score score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}

}
