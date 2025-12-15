// 실습 2

// web/assets/ssstock.csv
// Data(일자), Open(시가), High(고가), Low(저가), Close(종가), Adj Close(조정종가), Volume(거래량)

// '삼성전자 주가데이터 가져오기' 버튼을 클릭하면 HTML에 주가데이터 표시하기

/*
// - loadBtn 버튼 가져와서 클릭이벤트리스너 등록하기
document.querySelector('#loadBtn').addEventListener('click', e => {
    console.log('hello');
});
    
    
    
// - css파일 읽어오기
import { readFile } from 'fs/promises';
const csv = await readFile('web/assets/ssstock.csv', 'utf-8');

// const LINE_CHAR = navigator.platform.toLowerCase().includes('win') ? '\r\n' : '\n';
const lines = csv.trim().split('\n');

// *************************************제목: headers
const headers = lines[0].split(',');
console.log(headers);

// *************************************제목과 정보 객체: data
const data = lines.slice(1).map(line => { // 제목 제외한 아래 라인들 각각에 대해 (두번째 라인부터 하나씩 받아서)
        const values = line.split(','); // 한 줄의 데이터를 ,로 잘라서 배열로 (콤마로 분리)
        // 열이름과 열의 데이터로 객체 생성
        const obj = Object.fromEntries(headers.map((h, i) => [h, values[i]]));
        return obj;
});
    
// console.log(data);


// - #dataDiv 안에 테이블로 추가하기
const dataDiv = document.querySelector('#dataDiv');
let html = '<table><thead><tr>';

// //  ㄴ 제목: thead > tr > th
headers.forEach(ele => {
    html += `<th>${ele}</th>`
});
html += '</tr><tbody>';

// 각 객체에 대해서 출력
data.forEach((ele, i) => {
    html += `<tr><td>${ele.Date}</td><td>${ele.Open}</td><td>${ele.High}</td><td>${ele.Low}</td><td>${ele.Close}</td><td>${ele['Adj Close']}</td><td>${ele.Volume}</td></tr>`
});

html += '</tbody></thead></table>';
dataDiv.innerHTML = '';
dataDiv.innerHTML += html;

//  ㄴ 내용: tbody > tr > td
//  ㄴ 3.ex.js에서 긁어오기
// - csv -> 배열로 저장하기

*/

// 실습 2

// web/assets/ssstock.csv
// Data(일자), Open(시가), High(고가), Low(저가), Close(종가), Adj Close(조정종가), Volume(거래량)

// '삼성전자 주가데이터 가져오기' 버튼을 클릭하면 HTML에 주가데이터 표시하기

// 실습 2
// 삼성전자 주가 CSV 데이터를 불러와 테이블로 출력

// 요소 취득
const loadBtn = document.getElementById("loadBtn");
const dataDiv = document.getElementById("dataDiv");

// 버튼 클릭 처리
loadBtn.addEventListener("click", () => {
    fetch("assets/ssstock.csv") // 데이터 로딩
    .then(response => {
        if (!response.ok) throw new Error("CSV 파일 로딩 에러!"); // 응답 에러 처리
        return response.text(); // json은 response.json(), css는 response.text()
    })
    .then(csvText => renderTable(csvText)) // 테이블 표시를 위해 CSV문자열 전달
});

// CSV 문자열을 받아 테이블 표시
function renderTable(csvText) {

    const rows = csvText.trim().split('\n'); // 라인별 배열

    const table = document.createElement("table"); // table 요소 생성

    rows.forEach((row, index) => { // 각 행과 인덱스
        const tr = document.createElement("tr"); // tr 요소 생성
        const cols = row.split(","); // 각 행을 ,로 분리한 배열

        cols.forEach(col => { // 각 행의 열 수만큼 반복
            const cell = document.createElement(index === 0 ? "th" : "td"); // 첫 행인 경우 th, 나머지 td
            cell.textContent = col.replace(/"/g, ""); // " 제거
            tr.appendChild(cell); // tr에 th 또는 td 추가
        });

        table.appendChild(tr); // table에 tr 추가
    });

    dataDiv.innerHTML = ""; // div 초기화
    dataDiv.appendChild(table); // div에 table 추가
}
