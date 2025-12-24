// DOM 실습 2 : 동적 아이템 구현

// 1. 추가버튼 누르면 li 생성
const list = document.querySelector('#list'); //ul
const addBtn = document.querySelector('#add');
const removeBtn = document.querySelector('#remove');
const initBtn = document.querySelector('#init');
const selRemoveBtn = document.querySelector('#selRemove');
const txt = document.querySelector('#txt');

let count = 1; // list의 개수

addBtn.addEventListener('click', () => {
    const li = document.createElement('li');
    li.setAttribute('data-order', count);
    li.textContent = count++;
    list.appendChild(li);
});

// 2. 삭제버튼 누르면 마지막 li 제거

removeBtn.addEventListener('click', () => {
    // ul의 자식들
    if(count > 1) {
        list.removeChild(list.lastElementChild); // ul의 마지막 자식 제거
        count--;
    }
});

// 3. 초기화 버튼 누르면 모든 li 제거

initBtn.addEventListener('click', () => {
    list.innerHTML='';
    count=1;
});

// 4. 텍스트 상자에 번호 넣고 번호선택 삭제 버튼 누르면 해당 li 제거 (순서는 1부터)
// getAttribute해서 data-order에 번호와 맞는걸로 삭제
selRemoveBtn.addEventListener('click', () => {

    // 배열에서 제공된 테스트 함수를 만족하는 첫 번째 '요소'를 반환합니다.
    const li = [...document.querySelectorAll('li')].find( ele => {
        ele.dataset.order == txt.value;
    });
    
    // 탐색한 li가 존재한다면 list에서 삭제
    if(li) list.removeChild(li);

    // list 내에 자식 요소가 없다면 count=1;
    if(list.childElementCount==0) count=1;

});
