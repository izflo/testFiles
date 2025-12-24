// 실습 : 말 달리자!

// 경주시작 버튼을 누르면 경주가 시작되고 소요시간이 증가
// 스페이스바를 누르면 경주마가 10px씩 우측으로 이동
// 우측 결승라인에 도달하면 경주소요시간을 경주시작 버튼 우측에 표시
// 다시시작 버튼으로 경주 다시 시작

const start = document.querySelector('#start');
const time = document.querySelector('#time');

const againBtn = document.createElement('button');
againBtn.textContent = '다시 시작';
document.querySelector('p').appendChild(againBtn);

// 화면상에서 안보이게
againBtn.style.display = 'none';


let runInterval = null;
let runTime = 0; // 경주 완료시 몇초인지 표시할 때

// start 버튼 클릭 이벤트 추가
// 클릭 되는 순간 밀리초를 0.00초 형식(setInterval 간격을 10으로 설정)으로 time에 출력
start.addEventListener('click', e => {
    // if(runInterval!=null) return; // 중복 방지
    start.setAttribute('disabled', true);

    let count = 0;
    runInterval = setInterval(() => {
        time.textContent = `${(++count/100).toFixed(2)}초`
        runTime = time.textContent;
    }, 10);

});


// 결승라인
const finish = document.querySelector('#finish');
const horse = document.querySelector('#img');
let runCnt = 0;


// 스페이스바 키보드 이벤트(스페이스바 keyCode값 가져오기) 등록
document.addEventListener('keydown', e => {
    if(runInterval == null) return;

    if(e.keyCode == 32) {
        
        // 경주시작이 안됐을 때는 실행 안되게
        if(start.getAttribute('disabled') == null) return;

        // 결승선에 도달하기 전까지는 10씩 나아가기
        if(340 - parseInt(getComputedStyle(horse).left) > 0) {    
            runCnt += 10;
            horse.style.left = `${runCnt}px`;
        } else {   // 결승선에 도달하면
            clearInterval(runInterval);

            // 경주완료 (몇 초) 형식으로 time 변경하기
            // runtime 받아오기
            time.textContent = `경주완료 (${runTime})`;
            // 딱 한 번만 실행하고 싶은데!!!! 됐따!!!!!!!!!!
            createAgainBtn();
            runInterval = null; 
        }
    }
});


 // 결승선에 도달하면
// 2. 다시 시작 버튼 만들기
// 3. 경주 시작 버튼 활성화
// 4. runCnt = 0으로 만들기

// 다시시작 버튼 생성 함수
const createAgainBtn = () => {
    againBtn.style.display = 'inline-block';

    // 다시시작 누르면 경주시작 버튼 활성화 되게
    againBtn.addEventListener('click', e => {
        start.removeAttribute('disabled');

        // 말, 버튼 초기화
        horse.style.left = 0;
        runCnt = 0;
        againBtn.style.display = 'none';
        time.textContent = `0.00초`;
    });
};
