// 이벤트 실습 2 : 배경색 변경
//  - outer 클릭 -> 배경색 임의색상으로 변경
//  - inner 클릭 -> outer, inner 배경색 임의색상으로 변경
//  - btn 클릭 -> btn, outer, inner 임의색상으로 변경

// 임의색상
// rgb(0-255, 0-255, 0-255);
// Math.floor(Math.random() * 254)

// 배경 스타일 변경
// element.style.backgroundColor = 'yellow';

// 임의 색상 생성 함수
const getRandomColor = () => {
    return `rgb(${Math.floor(Math.random() * 256)},${Math.floor(Math.random() * 256)},${Math.floor(Math.random() * 256)})`
};

/*

document.querySelector('#outer').addEventListener('click', e => {
    e.target.style.backgroundColor = getRandomColor();

    // e의 자식들은  e.stopPropagation(); 해주기
    e.target.firstElementChild;
}, false);

document.querySelector('#inner').addEventListener('click', e => {
    e.target.style.backgroundColor = getRandomColor();

    console.log(e.target.parenttNode);
    // style.backgroundColor = getRandomColor();
    // 버블링 막아야함
    e.stopPropagation();

});
*/


/*
btn ->
    이벤트 위임 (event delegation)
        - 하위 요소에서 발생한 이벤트를 하위 요소가 아닌 상위 요소에서 처리
        - 이벤트 위임을 통해 이벤트 처리를 일원화/단순화 시킬 수 있음
*/

const outer = document.querySelector('#outer');
const inner = document.querySelector('#inner');
const btn = document.querySelector('#btn');


outer.addEventListener('click', e => {

    const innerBG = inner.style.backgroundColor ? inner.style.backgroundColor : 'white';
    
    switch(e.target.id) {
        case 'outer':
            outer.style.backgroundColor = getRandomColor();
            inner.style.backgroundColor = innerBG;
            break;
        case 'inner' :
            outer.style.backgroundColor = getRandomColor();
            inner.style.backgroundColor = getRandomColor();
            break;
        case 'btn' :
            outer.style.backgroundColor = getRandomColor();
            inner.style.backgroundColor = getRandomColor();
            btn.style.backgroundColor = getRandomColor();
            break;
    }
    
});
