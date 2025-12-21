// REST API 실습
// - json-server 사용 (studentsscore.json 서비스) : http://localhost:7777/studentscore
// - REST API 사용
// - axios 사용

// 1. 데이터 가져오기 클릭 > 테이블에 데이터 렌더링
// 2. 아이디, 이름, 국어, 영어, 수학 입력 후 등록 클릭 > 데이터 등록하고 테이블 리렌더링

// **중복 없애기, 기능별로 함수로 나누기**

// 1) 클릭이벤트리스너 > e.target.textContent 가져와서 switch문으로 실행
// render 함수 만들기


// render(response를 객체로 변경한 리스트)
// 테이블 초기화
// axios.get으로 값 가져오기 -> recieveData()에 정의


// - 데이터 가져오기
// axios.get -> response 받아오기 -> 배열로 가져와짐
// 객체의 키값: Object.keys(response) 받아와서 객체만큼 반복하고 thead에 tr로 넣어주기
// 객체의 value 받아와서 객체만큼 반복하고 tbody에 tr로 넣어주기

// - 데이터 등록하기
// input의 값들(getText) 배열로 저장해두기.
// putData(input에 입력된 값 배열)에서 post 실행
// post의 입력값에 넣어주기
// post에 보낸다음에 다시 렌더링

// 4. 데이터가져오기 버튼 옆에 '과목별총점' 버튼 추가하고 
const subjectSumBtn = $('<button></button>').text('과목별총점');
$('p').last().append(subjectSumBtn);

let isTotal = false; //과목별 총점 눌렀을 때 처리할 플래그

// 버튼 이벤트 리스너
$('button').click(e => {
    switch(e.target.textContent) {
        case '등록':
            const inputs = document.getElementsByTagName('input');
            const inputValues = Array.from(inputs).map(input => {
                if(input.hasAttribute('class')) return parseInt(input.value); //score이면 숫자로 변환
                else return input.value;
            })

            //유효성 검사
            // input 형식이 모두 맞아야만 post
            const inputCheck = validTest(inputValues); // 형식이 모두 맞아야만 true반환
            if(!inputCheck) alert('형식에 맞춰 작성해주세요!');
            else putData(inputValues); // inputValues: post할 데이터

            break;

        case '데이터가져오기': renderData(); break;
        case '과목별총점' : 
            isTotal = true;
            renderData();
    }
})
        
    
//유효성 검사
const validTest = (inputValues) => {
    const check =  inputValues.every( (input, index) => {
        if(index == 0) return /^[0-9]+$/.test(input); //id(숫자 하나 이상)
        else if(index == 1) return /^[a-zA-Z가-힣]{2,}$/.test(input); //이름 (영어, 한글 두 글자 이상)
        else return /^(100|[1-9]?[0-9])$/.test(input); // 나머지 성적들 (최대 100점)
    });

    return check; //input의 유효성이 전부 맞으면 true 반환
};
    
// console.log('데이터 get하고 테이블에 출력');
async function renderData() {
    const url = 'http://localhost:7777/studentscore';
    const response = await fetch(url);
    const post = await response.json();
    createTable(post);
}

async function putData(inputValues) {
    // - 데이터 등록하기

    //1차원 배열 to 중첩 객체
    const [id, name, kor, math, eng] = inputValues;

    const newData = {
        id,
        name,
        score: {
            kor,
            math,
            eng
        }
    };

    const url = 'http://localhost:7777/studentscore';
    await fetch( // post할 동안 다른 작업 일시 중단
        url,
        {
            method: 'POST',
            headers: { 'content-type': 'application/json' },
            body: JSON.stringify(newData)
        }
    );
    renderData();
}


const createTable = studentArr => {
    // 3. 학생별 총점/평균 열 추가

    // header
    $('thead').html('<tr><th>아이디</th><th>이름</th><th>국어</th><th>수학</th><th>영어</th><th>총점</th><th>평균</th><th></th></tr></tr>');

    let tableHTML = '';
    // 데이터 받아오기 -> 중첩 객체 to 1차원 배열
    const studentInfo = studentArr.map( student => {
        const {id, name, score: {kor, math, eng}} = student;

        //총점, 평균 구하기
        const scoreCnt = Object.values(student.score).length; // 점수의 개수
        const sum = [kor, math, eng].reduce((acc, curr) => acc+curr); // 위에 구조분해할당으로 받은 값
        const avg = (sum/scoreCnt).toFixed(2);

        return [id, name, kor, math, eng, sum, avg]; // 아이디, 이름, 성적, 총점, 평균이 담긴 배열
    });

    // 테이블에 출력
    studentInfo.forEach(student => {
        tableHTML += '<tr>';
        student.forEach(td => {
            tableHTML += `<td>${td}</td>`
        });
        tableHTML += '<td><button class="delete">삭제</button></td></tr>';

    });
    $('tbody').html(tableHTML);

    

const deleteBtn = $('button.delete');
console.log(deleteBtn.parent().siblings());
(Array.from(deleteBtn)).forEach(element => {
    console.log(element.parentElement.parentElement); // 각 줄의 tr
})



    if(isTotal) {
        // 과목별 총점 클릭하면**********
        //**이 세개를 한 번에 하는 방법은 없을까?
        const korTotal = studentArr.map(student => student.score.kor).reduce((acc,curr) => acc+curr);
        const mathTotal = studentArr.map(student => student.score.math).reduce((acc,curr) => acc+curr);
        const engTotal = studentArr.map(student => student.score.eng).reduce((acc,curr) => acc+curr);
        
        $('tfoot').html(`<tr><th colspan="2">총점</th><td>${korTotal}</td><td>${mathTotal}</td><td>${engTotal}</td><th colspan="2"></th></tr>`);
        
    }
};

async function deleteStudent(inputValues) {
    //1차원 배열 to 중첩 객체 **함수로 만들기
    // const [id, name, kor, math, eng] = inputValues;

    // const newData = {
    //     id,
    //     name,
    //     score: {
    //         kor,
    //         math,
    //         eng
    //     }
    // };

    // const url = 'http://localhost:7777/studentscore';
    // await fetch( // post할 동안 다른 작업 일시 중단
    //     url,
    //     {
    //         method: 'DELETE',
    //         headers: { 'content-type': 'application/json' },
    //         body: JSON.stringify(newData)
    //     }
    // );

    // renderData();
    console.log('삭제 버튼 눌렀음!');
};


// [3~6 주말과제 : 일요일 오후 12시까지 '이름_rest.zip' 제출]

// 5. 각 학생의 국어/영어/수학 점수 수정 기능 추가
// 6. 각 학생 데이터 삭제 기능
