package collection.ex;

public class Student {

	private Score score; // 점수 정보가 들어가 있음
	private String name;

	public Student() {
	}

	public Student(String name, int kor, int eng, int math) {
		// 이걸 super()로 할 수 있는 방법은?
		this.score.setKor(kor);
		this.score.setEng(eng);
		this.score.setMath(math);
		this.name = name;
	}

	public Student(String name, Score score) {
		super();
		this.score = score;
		this.name = name;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name + "은 " + this.score;
	}

}
