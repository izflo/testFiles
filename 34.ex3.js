// event 실습 3

// 10행 10열의 테이블을 만들고 각 칸에 1~100의 수 중 임의의 수를 넣음
// 숫자를 2개 이상 선택하고 합계버튼을 누르면 수의 합계를 출력

// Fisher-Yates Shuffle Algorithm (배열내의 요소 섞는 알고리즘)
function shuffleArray(array) {
  for (let i=array.length-1; i>0; i--) { // 인덱스를 거꾸로 순회
    const j = Math.floor(Math.random()*(i+1)); // 임의의 인덱스 획득
    [array[i], array[j]] = [array[j], array[i]]; // 인덱스 교환
  }
  return array; // 섞인 배열을 반환
}

/*
<<< Devide & Conquer "분할해서 정복한다!" >>>
소프트웨어 공학 책에 나오는 대원칙입니다.

1. 문제를 최대한 잘게 나누세요.
2. 나누어진 조각들의 순서를 정하세요.
3. 순서대로 코드를 작성하면 완성!

[문제]
// 10행 10열의 테이블을 만들고 각 칸에 1~100의 수 중 임의의 수를 넣음
// 숫자를 2개 이상 선택하고 합계버튼을 누르면 수의 합계를 출력

[솔루션]
1. 데이터
  1) 1~100까지 정수를 저장할 배열이 필요
  2) 선택한 정수들을 저장할 배열이 필요
2. 기능
  1) 1~100까지 정수를 배열에 저장
  2) 배열에 저장된 1~100까지의 정수를 알고리즘을 통해 섞음
  3) 동적으로 테이블을 생성하여 화면에 표시
  4) 합계 버튼과 결과표시 텍스트상자를 동적으로 생성하여 화면에 표시
  5) 테이블 클릭시 클릭한 TD의 숫자를 가져와서 1-2)배열에 저장
  6) 버튼 클릭시 1-2)배열에 있는 숫자들의 합을 연산
  7) 연산결과를 텍스트상자에 표시
*/




//선택한 정수들을 저장할 배열(전역)
let globalSelectedArr = [];

// 100까지 정수 배열에 저장해서 섞는 함수
const makeArr = () => {
  
  const hundredArr = [];

  for(let i=1; i<=100; i++) hundredArr.push(i);

  return shuffleArray(hundredArr);
  
};


// 동적으로 테이블을 생성하여 화면에 표시

const makeTable = () => {

  const arrList = makeArr();

  let html = `<table><tbody>`;
  for(let i=0; i<10; i++) {
    html += `<tr>`;
    for(let j=0; j<10; j++) {
      html += `<td>${arrList[i * 10 + j]}</td>`; // 1차원 배열을 2차원 배열로 저장하는 법
    }
    html += `</tr>`;
  }
  html += `</tbody></table>`;

  //하단 html 생성
  let bottomHtml = '<p><button id="add">합계<button id="sub">지우기</button>&nbsp;<input type="text"></p>';

  //body에 생성한 HTML 추가
  document.querySelector('body').innerHTML = html + bottomHtml;


  // td마다 클릭이벤트리스너 연동 
  // ***************이벤트 위임 가능******************************
  // table로
  document.querySelector('table').addEventListener('click', e => {
    // 클릭한거 스타일 변경
    e.target.style.backgroundColor = 'red';
    e.target.style.color = 'white';

    // 선택한 번호를 정수로 가져오기
    const selNum = parseInt(e.target.textContent);
    tdSum(selNum);
  });

}


makeTable();

// 클릭한 td 합 저장하는 함수
const tdSum = e => {
  // if(globalSelectedArr.indexOf(e)<0) : 선택번호 저장 배열에 번호가 없다면
  if(globalSelectedArr.includes(e)) {
    alert('이미 입력한 번호입니다. 다른 번호를 눌러주세요!');
  }else globalSelectedArr.push(e);
};

//배열에 있는 숫자들의 합을 연산하는 함수
const calcSum = () => {
  return globalSelectedArr.reduce((acc, curr) => +acc + +curr, 0);
};

//합계 버튼과 결과표시 텍스트상자를 동적으로 생성하여 화면에 표시
document.querySelector('button#add').addEventListener('click', e => {
  if(globalSelectedArr.length < 2) {
    e.preventDefault();
    alert(`2개 이상 눌러주세요! 현재 입력 개수 : ${globalSelectedArr.length}`);
  } else document.querySelector('input').value = calcSum();
});

document.querySelector('button#sub').addEventListener('click', e => {
  if(globalSelectedArr.length == 0) {
    e.preventDefault();
    alert(`다 제거했습니다!`);
  } else {
    // globalSelectedArr.pop();
    console.log(globalSelectedArr.pop());
    // pop한 번호와 일치하는 td를 가져와서 색상 변경하기
  }
});
