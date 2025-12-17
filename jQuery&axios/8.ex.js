// jQeury와 axios 활용해서 데이터 로딩

// CSV : https://raw.githubusercontent.com/datasets/population/master/data/population.csv
// JSON : https://jsonplaceholder.typicode.com/posts
// XML : https://www.w3schools.com/xml/plant_catalog.xml

// 각 버튼을 누르면 위 포맷별 URL을 AJAX GET요청해서
// 아이디가 resultDiv인 div내에 테이블을 생성해서 데이터를 렌더링



$(function() {

    // 엔드포인트
    const endPoints = {
        CSV : 'https://raw.githubusercontent.com/datasets/population/master/data/population.csv',
        JSON : 'https://jsonplaceholder.typicode.com/posts',
        XML : 'https://www.w3schools.com/xml/plant_catalog.xml'
    };
    
    
    // 결과표시 DIV
    const resultDiv = $('#resultDiv');

    // // 클릭 이벤트 처리
    $('button').click(e => ajaxRequest(e.target.id, endPoints[e.target.id]));
    
    const ajaxRequest = (format, url) => {
        // axios도 포맷마다 전송하는 게 다르면 switch로 렌더링
        axios.get(url)
         .then(function (response) {
           // 성공 핸들링
           //데이터 표시하는 함수
           render([format, response.data]);
         })
         .catch(function (error) {
           // 에러 핸들링
           console.log('오류입니다');
         })
         .finally(function () {
           // 항상 실행되는 영역
         });
    };

    // 데이터 렌더링
    const render = resultArr => {
        switch (resultArr[0]) {
            case 'CSV': {
                resultDiv.text(resultArr[1]);
                break;
            }
            case 'JSON': {
                resultDiv.text(resultArr[1]);
                break;
            }
            case 'XML': {
                resultDiv.text(resultArr[1]);
            }
        }
    };

});
