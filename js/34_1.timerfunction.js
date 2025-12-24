/*
    Javascript 비동기 timer 함수
    
    1. setTimeout(콜백, 밀리초) : 밀리초 후에 콜백함수 수행

    2. clearTimeout(타임아웃변수) : 타임아웃 제거

    3. setInterval(콜백함수, 밀리초) : 밀리초 간격으로 콜백함수 수행

    4. clearInterval(인터벌변수) : 인터벌 제거
*/

// setTimeout (밀리초 후에 한 번만 실행)
document.querySelector('#timeoutBtn').addEventListener('click', e => {
    setTimeout(() => {
        alert('3초가 지났어요');
    }, 3000); //3초
});

// clearTimeout
let timeoutId = null;
document.querySelector('#reserveBtn').addEventListener('click', e => {
    // 취소하려면 settimeout을 변수 안에 담아둬야함*****
    timeoutId = setTimeout(() => {
        alert('5초 후 실행되었습니다!');
    }, 5000);

    alert('5초 후 실행이 예약되었습니다!');
});
document.querySelector('#cancelBtn').addEventListener('click', e => {
    clearTimeout(timeoutId); // 취소하고자 하는 타이머 변수 넣으면 됨
    alert('예약이 취소되었습니다.')
});

// setInterval / clearInterval
let intervalId = null;
let count = 0;

document.querySelector('#startIntervalBtn').addEventListener('click', e => {
    // *********************************************************************
    if(intervalId!=null) return; // 중복 실행 방지****
    intervalId = setInterval(() => { // 중복 처리 안 하면 계속 생김 -> error
        document.querySelector("#counter").textContent = ++count;
    }, 1000);
    // *********************************************************************
});

document.querySelector('#stopIntervalBtn').addEventListener('click', e => {
    clearInterval(intervalId);
    intervalId = null; // 타이머 그만 돌리는 거
});

// 디지털 시계
setInterval(() => {
    const now = new Date(); // 1초마다 현재 날짜, 시간 가져옴
    const hours = now.getHours().toString().padStart(2, '0'); //padStart : 두자리수인데 한자리수면 앞에 0으로 채워라
    const minutes = now.getMinutes().toString().padStart(2, '0'); //padStart : 두자리수인데 한자리수면 앞에 0으로 채워라
    const seconds = now.getSeconds().toString().padStart(2, '0'); //padStart : 두자리수인데 한자리수면 앞에 0으로 채워라
    const ampm = hours > 12 ? '오후' : '오전';
    document.querySelector('#clock').textContent = `${ampm} ${hours}:${minutes}:${seconds}`;


}, 1000);

// 비동기여서 각자각자 돔 -> thread처럼
