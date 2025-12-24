// 이벤트 실습 1 : 숫자카운터
// 증가 버튼 클릭 -> 1씩 증가
// 감소 버튼 클릭 -> 1씩 감소
// 텍스트 상자에 직접 입력 못하도록

const increase = document.querySelector('#increase');
const decrease = document.querySelector('#decrease');
const counter = document.querySelector('#counter');

increase.addEventListener('click', e => {
    counter.value = +counter.value+1;
});

decrease.addEventListener('click', e => {
    counter.value = +counter.value-1;
});

// counter.addEventListener('mousedown', e => {
//     e.preventDefault();
//     alert('텍스트 상자에 입력할 수 없습니다.');
// });

counter.addEventListener('focus', e => {
    counter.blur();
});
