// 실습 : 디지털 시계

// 작동 버튼 클릭하면 1초씩 디지털 시계가 움직임
// 중지 버튼 클릭하면 디지털 시계가 중지
// clock이라는 id를 가진 p요소내에 아래 형식으로 출력
// 형식 : XXXX년 XX월 XX일 X요일 오전/오후 XX시 XX분 XX초

const clock = document.querySelector('#clock');

// 작동 버튼 클릭하면 1초씩 디지털 시계가 움직임
// -> 중복 작동하면 안되니까 중복 방지
// setInterval 1000로 설정
// ㄴ 함수 안에다가 new today()받아서 실행(getYear, getMonth, getDate, getDay, ....)
// ㄴ padStart 해서 두자리수 맞추기
// clock.textContent = ${}로 다 넣어주기

let timeInterval = null;
let count = 0;

document.querySelector('#start').addEventListener('click', e => {
    if(timeInterval!=null) return; //중복 방지
    
    timeInterval = setInterval(() => {
        const week = ['일', '월', '화', '수', '목', '금', '토'];

        const today = new Date();

        const year = today.getFullYear().toString();
        const month = (today.getMonth()+1).toString().padStart(2, '0');
        const date = today.getDate().toString().padStart(2, '0');
        let day = week[today.getDay()];
        // 24시간 말고 12시간으로 표시
        // const hours = today.getHours().toString().padStart(2, '0');
        // 마지막 12는 12시일 때 나누어 떨어져서 0 나오니까 12시로 표기해야돼서 그런 것  
        const hours = (today.getHours() % 12 ? today.getHours() % 12 : 12).toString().padStart(2, '0');
        const ampm = today.getHours() <= 12 ? '오전' : '오후';
        const minutes = today.getMinutes().toString().padStart(2, '0');
        const seconds = today.getSeconds().toString().padStart(2, '0');
        
        clock.textContent = `${year}년 ${month}월 ${date}일 ${day}요일 ${ampm} ${hours}시 ${minutes}분 ${seconds}초`;

    }, 1000);
});


// 중지 버튼 -> clear함수 해주고 setinterval 변수 null로 변경
document.querySelector('#stop').addEventListener('click', e => {
    clearInterval(timeInterval);
    timeInterval = null; // 타이머 그만 돌리는 거
});
