// AJAX 데이터 로딩 실습

// CSV : https://raw.githubusercontent.com/datasets/population/master/data/population.csv
// JSON : https://jsonplaceholder.typicode.com/posts
// XML : https://www.w3schools.com/xml/plant_catalog.xml

// 각 버튼을 누르면 위 URL을 AJAX GET요청해서
// 아이디가 resultDiv인 div내에 HTML을 테이블을 생성해서 데이터 렌더링
// <button id="csvBtn">CSV데이터 로딩</button>
// <button id="jsonBtn">JSON데이터 로딩</button>
// <button id="xmlBtn">XML데이터 로딩</button>

// -버튼 이벤트리스너 추가하기

// CSV 요청하는 법
// JSON 요청하는 법
// XML 요청하는 법
// 테이블 표현법

document.querySelector('#csvBtn').addEventListener('click', e => {
    
    
    // xhr 객체 생성
    const xhr = new XMLHttpRequest(); // 서버와 통신을 위한 xhr 객체 생성
    xhr.onreadystatechange = e => {

        if(xhr.readyState==4 && (xhr.status==200||xhr.status==201)) {
            // xhr.responseText 프라퍼티의 값 : 서버가 보낸 응답 문자열
            // json문자열 -> javascript 객체로 변환
            // 변환된 객체로 테이블 생성
            const csv = xhr.responseText;
            const LINE_CHAR = navigator.platform.toLowerCase().includes('win') ? '\r\n' : '\n';
            const lines = csv.trim().split(LINE_CHAR); //줄바꿈 문자 기준으로 잘라서
            // console.log(lines);
            
            const table = document.createElement('table');

            lines.forEach((line, index) => {
                // 라인 한 줄마다
                const tr = document.createElement("tr"); // tr 요소 생성

                // line의 요소들에 접근
                const items = line.split(',');
                // 한 줄 완성
                items.forEach(item => {
                    const cell = document.createElement(index === 0 ? "th" : "td"); // 첫 행인 경우 th, 나머지 td
                    cell.textContent = item;
                    tr.appendChild(cell);
                });
                table.appendChild(tr);
            });

            // dataDiv.innerHTML = ""; // div 초기화
            document.querySelector('#resultDiv').innerHTML = '';
            document.querySelector('#resultDiv').appendChild(table);// div에 table 추가

            /* innerHTML방식----------------------------------------------------------------------
            

            let html = '<table><thead><tr>';
            const titles = lines[0].split(',');
            // thead
            for(let title of titles) {
                html += `<th>${title}</th>`;
            }
            html += '</tr><tbody>';

            //tbody
            for(let line of [...lines].slice(1)) {
                console.log(line);
                const items = line.toString().split(',');

                html += '<tr>';
                // 출력
                for(let item of items) {
                    html += `<td>${item}</td>`;
                }
                html += '</tr>';
            }

            html += '</tbody></table>';
            document.querySelector('#resultDiv').innerHTML = html;
            */
            
        }
    };


    // GET 요청 (원격)
    xhr.open('GET', 'https://raw.githubusercontent.com/datasets/population/master/data/population.csv', true);
    xhr.send();


});


// -----------------------------------JSON-----------------------------------------------

document.querySelector('#jsonBtn').addEventListener('click', e => {
    
    // xhr 객체 생성
    const xhr = new XMLHttpRequest(); // 서버와 통신을 위한 xhr 객체 생성
    xhr.onreadystatechange = e => {

        if(xhr.readyState==4 && (xhr.status==200||xhr.status==201)) {
            // xhr.responseText 프라퍼티의 값 : 서버가 보낸 응답 문자열
            // json문자열 -> javascript 객체로 변환
            // 변환된 객체로 테이블 생성
            const obj = JSON.parse(xhr.responseText);
            console.log(obj);


            //테이블 생성
            let html = '<table><thead><tr>';
            // thead 만들기
            for(let title of Object.keys(obj[0])) {
                html += `<th>${title}</th>`;
            }
            html += '</tr><tbody>';

            // tbody 만들기
            for(let user of obj) {
                html += `
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.id}</td>
                    <td>${user.title}</td>
                    <td>${user.body}</td>
                </tr>
                `;
            }

            html += '</tbody></table>';
            document.querySelector('#resultDiv').innerHTML = '';
            document.querySelector('#resultDiv').innerHTML = html;
        }
    };


    // GET 요청 (원격)
    xhr.open('GET', 'https://jsonplaceholder.typicode.com/posts', true);
    xhr.send();

});




// -----------------------------------XML------------------------------------------------
document.querySelector('#xmlBtn').addEventListener('click', e => {
    
    
    // xhr 객체 생성
    const xhr = new XMLHttpRequest(); // 서버와 통신을 위한 xhr 객체 생성
    
    xhr.onreadystatechange = e => {

        if(xhr.readyState==4 && (xhr.status==200||xhr.status==201)) {

            const xmlDoc = xhr.responseXML; // xml 응답            
            const catalog = xmlDoc.getElementsByTagName('CATALOG')[0];

            // catalog.children -> plant들
            //plant들 중 첫번 째 -> catalog.children[0]
            // 첫번 째 plant의 자식들을 돌고 싶다면?
            // catalog.children.firstChild

            // // for문 돌면서 thead 만들기
            
            let html = '<table><thead><tr>';
            // // for문 돌면서 thead 만들기
            for(let title of catalog.children[0].children) {
                html += `<th>${title.nodeName}</th>`;
            }
            html += '</tr><tbody>';

            // for문 돌면서 tbody에 내용 입력하기
            // plant들마다 순회하면서 (catalog.children 순회)
            // plant.getTagName('COMMON')[0].textContent -> 태그들마다 안에 내용
            for(let plant of catalog.children) {

                html += '<tr>';
                // getElementsByTagName을 순회하면 한 줄로 요약할 수 있지 않을까?
                for(let tagName of plant.children) {
                    html += `<td>${tagName.textContent}</td>`;
                }
                html += '</tr>';

                // html += `
                //     <tr>
                //         <td>${plant.getElementsByTagName("COMMON")[0].textContent}</td>
                //         <td>${plant.getElementsByTagName("BOTANICAL")[0].textContent}</td>
                //         <td>${plant.getElementsByTagName("ZONE")[0].textContent}</td>
                //         <td>${plant.getElementsByTagName("LIGHT")[0].textContent}</td>
                //         <td>${plant.getElementsByTagName("PRICE")[0].textContent}</td>
                //         <td>${plant.getElementsByTagName("AVAILABILITY")[0].textContent}</td>
                //     </tr>
                // `;
            }

            html += '</tbody></table>';
            document.querySelector('#resultDiv').innerHTML = '';
            document.querySelector('#resultDiv').innerHTML = html;
        }
    };

    // GET 요청 (원격)
    xhr.open('GET', 'https://www.w3schools.com/xml/plant_catalog.xml', true);
    xhr.send();


});