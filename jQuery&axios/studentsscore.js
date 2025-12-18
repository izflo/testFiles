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


// - 데이터 등록하기
// input의 값들(getText) 배열로 저장해두기.
// putData(input에 입력된 값 배열)에서 post 실행
// post의 입력값에 넣어주기
// post에 보낸다음에 다시 렌더링

$('button').click(e => {
    switch(e.target.textContent) {
        case '등록':
            putData();
            break;
            case '데이터가져오기':
                renderData();
                
                
            }
        })
        
    // - 데이터 가져오기
    // axios.get -> response 받아오기 -> 배열로 가져와짐
    // 객체의 키값: Object.keys(response) 받아와서 객체만큼 반복하고 thead에 tr로 넣어주기
    // 객체의 value 받아와서 객체만큼 반복하고 tbody에 tr로 넣어주기
    
    
    const renderData = () => {
    // console.log('데이터 get하고 테이블에 출력');

        axios.get('http://localhost:7777/studentscore') // 가져올 데이터
            .then(resp => {
                const studentArr = resp.data;
                // header
                // const header = Object.keys(studentArr[0]);

                $('thead').html('<tr><th>아이디</th><th>이름</th><th>국어</th><th>영어</th><th>수학</th></tr>');


                let tableHTML = '';
                // 데이터 받아오기
                // 아이디, 이름, 성적이 담긴 배열
                const studentsScore = studentArr.map(student => Object.values(student));
                // console.log(studentsScore);

                //---------------------------------------------------------------------------------------------------------------
                // filter함수
                // console.log(...studentArr);
                // student의 values가 object가 아닌 것들 먼저 담기
                // 그다음에 아닌 것들 담고
                // [,]로 이어주기

                studentsScore.forEach(student => {
                    // console.log(student);
                    tableHTML += '<tr>';


                    // 객체인 것과 아닌 것을 다르게 표시해줘야함
                    student.forEach(td => {
                        tableHTML += `<td>${td}</td>`
                        if((typeof td) == 'object'){
                            // td.forEach(obj => tableHTML += `<td>${Object.values(obj)}</td>`);
                            // console.log(td);
                        }
                    });

                    tableHTML += '</tr>';
                });

                $('tbody').html('');
                $('tbody').html(tableHTML);

                // studentsScore을 돌면서 (map) isObject라면 그 요소 pop하고 그 요소의 values를 추가해줌
                // const valuesArr = studentsScore.map(student => {
                //     student.map(s => {
                //         if((typeof s) == 'object') {
                //             const scores = s;
                //             student.pop(s);
                //             student.push(...Object.values(scores));
                //         }
                //     });
                // }); // id, name


                // ****중첩 객체 때문에 오류 -> 풀어서 평탄화 시켜야함
                // studentArr 돌면서 student마다 value 꺼내서 테이블에 추가
                // studentsScore.forEach(student => {
                //     tableHTML += '<tr>';
                //     student.forEach(td => {tableHTML += `<td>${td}</td>`});
                //     tableHTML += '</tr>';
                // })
                // studentsScore.forEach(st => console.log(st));
            })
            .catch(err => {
                alert('요청 에러 발생!');
        });
}

const putData = () => {
    // console.log('입력된 데이터 post');
    renderData();
}


// [3~6 주말과제 : 일요일 오후 12시까지 '이름_rest.zip' 제출]
// 3. 학생별 총점/평균 열 추가
// 4. 데이터가져오기 버튼 옆에 '과목별총점' 버튼 추가하고 누르면 과목별 총점 행 추가
// 5. 각 학생의 국어/영어/수학 점수 수정 기능 추가
// 6. 각 학생 데이터 삭제 기능