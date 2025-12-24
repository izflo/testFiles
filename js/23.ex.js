
/*
   파일명 : 23.ex.js

    [클래스 실습]
    1. 학생(Student) 클래스를 생성
    2. 학생은 이름, 나이, 취미(여러개), 국어점수, 영어점수, 수학점수의 데이터를 가짐
    3. 학생 객체를 3개 생성
    4. 아래와 같이 출력
        총 3명의 학생 정보입니다!
        홍길동, 20세, 취미:골프, 스키 국어:100, 영어:100, 수학:100, 총점:300, 평균:100
        강감찬, 30세, 취미:독서, 낮잠 국어:90, 영어:90, 수학:90, 총점:270, 평균:90
        이순신, 40세, 취미:게임, 바둑 국어:80, 영어:80, 수학:80, 총점:240, 평균:80
*/


class Student {
    constructor(name, age, hobbies, kor, eng, math) {
        this._name = name;
        this._age = age;
        this._hobbies = hobbies;
        this._kor = kor;
        this._eng = eng;
        this._math = math;
    }

    printAll() {
        console.log(`${this._name}, ${this._age}세, 취미:${this._hobbies}, 국어:${this._kor}, 영어:${this._eng}, 수학:${this._math}, 총점:${this._kor+this._eng+this._math}, 평균:${(this._kor+this._eng+this._math)/3}`);
    }

    get name() {
        return this._name;
    }
    set name(name) {
        this._name = name;
    }

    get age() {
        return this._age;
    }
    set age(age) {
        this._age = age;
    }

    get hobbies() {
        return this._hobbies;
    }
    set hobbies(hobbies) {
        this._hobbies = hobbies;
    }

    get kor() {
        return this._kor;
    }
    set kor(kor) {
        this._kor = kor;
    }

    get eng() {
        return this._eng;
    }
    set eng(eng) {
        this._eng = eng;
    }

    get math() {
        return this._math;
    }
    set math(math) {
        this._math = math;
    }
}

// 홍길동, 20세, 취미:골프, 스키 국어:100, 영어:100, 수학:100, 총점:300, 평균:100
//         강감찬, 30세, 취미:독서, 낮잠 국어:90, 영어:90, 수학:90, 총점:270, 평균:90
//         이순신, 40세, 취미:게임, 바둑 국어:80, 영어:80, 수학:80, 총점:240, 평균:80


const st1 = new Student('홍길동', 20, ['골프', '스키'], 100, 100, 100);
const st2 = new Student('강감찬', 30, ['독서, 낮잠'], 90, 90, 90);
const st3 = new Student('이순신', 40, ['게임, 바둑'], 80, 80, 80);

console.log(`총 3명의 학생 정보입니다!`);
st1.printAll();
st2.printAll();
st3.printAll();
