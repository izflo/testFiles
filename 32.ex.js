// DOM 실습 1

// 아래 아이템들을 가진 리스트들을 DOM을 활용해서 HTML에 생성
// 사과, 딸기, 포도, 복숭아

const newItem = document.createElement('ul');

const fruit = ['사과', '딸기', '포도', '복숭아'];

const fragment = document.createDocumentFragment(); // DocumentFragment 생성
for(let i=0; i<fruit.length; i++) {
    const li = document.createElement('li');
    li.textContent = fruit[i];
    newItem.appendChild(li);
}
document.querySelector("body").append(newItem);


// 풀이 1
document.querySelector('body').innerHTML
    = '<ul><li>사과</li><li>딸기</li><li>포도</li><li>복숭아</li></ul>';

// 풀이 2
const ul = document.createElement('ul');
const items = ['사과', '딸기', '포도', '복숭아'];
items.forEach( (ele, i) => {
    const item = document.createElement('li');
    const itemtc = document.createTextNode(items[i]);
    item.appendChild(itemtc);
    ul.appendChild(item); //li를 ul에 붙이기
});
document.querySelector('body').appendChild(ul);


/*
const ul = document.createElement('ul');
const items = ['사과', '딸기', '포도', '복숭아'];
items.forEach( (ele, i) => {
    const item = document.createElement('li');
    item.textContent = items[i];
    ul.appendChild(item);
});
document.querySelector('body').appendChild(ul);

*/