// 공공데이터 API 호출 실습
// 17:00에 개별 발표

// 1. 공공데이터 포털(data.go.kr)에서 원하는 json데이터를 가져옵니다.
// 2. 데이터가져오기 버튼을 클릭하면 가져온 json데이터를 테이블에 표시합니다.
// 3. 검색기능을 구현합니다. (검색어를 입력하고 검색버튼을 클릭) trim()
// 4. 정렬기능을 구현합니다. (정렬할 컬럼을 선택하고 ASC/DESC 버튼을 클릭)
fetch('http://openapi.seoul.go.kr:8088/6b6f537a48697a6c34387764455247/json/bikeList/1/5/')
    .then(resp => resp.json())
    .then( obj => {
        const bicycleObj = obj.rentBikeStatus.row; // 자전거 리스트
        
        const theadList = {
            '대여소이름' : 'stationName', 
            '거치대개수' : 'rackTotCnt', 
            '자전거주차총건수' : 'parkingBikeTotCnt',
            '거치율' : 'shared'
        }

        // sortSel을 theadList로 만들기
        const sortSel = document.querySelector('#sortSel');
        Object.keys(theadList).forEach(opt => {
            const sortOpt = document.createElement('option');
            sortOpt.value = theadList[opt];
            sortOpt.textContent = opt;
            sortSel.appendChild(sortOpt);
        });

        // 객체를 배열로 변환, 이름 변경
        const bicycleList = bicycleObj.map(bcc => {
            return Object.values(theadList).map((val) => {
                if(val == 'stationName') {
                    return bcc[val].split('.')[1].trim();
                }
                else return bcc[val];
            })
        });

        const tbody = document.querySelector('tbody');
        document.querySelector('h1').textContent = '서울시 공공자전거 실시간 대여정보'; // 테이블 제목 설정
        
        currentArr = []; // 검색 후 정렬할 때 현재 정렬되어 있는 배열을 넘겨주기 위해
        let isAsc = true;
        
        document.querySelectorAll('button').forEach( btn => {
            btn.addEventListener('click', e => {
            switch (e.target.textContent) {
                case '검색':
                    searchTitle();
                    break;
        
                case 'ASC':
                    isAsc = true;
                    setTable(currentArr);
                    break;
                
                case 'DESC':
                    isAsc = false;
                    setTable(currentArr);
            }
            });
        })
        
        const searchTitle = () => {
            // input 값 가져오기
            const keyword = document.querySelector('#searchKeyword').value;
            
            if(!/.{2,}/g.test(keyword)) {
                alert('2글자 이상 입력하세요.');
                return;
            }

            // 대여소 이름 list 가져와서 배열 만들기
            
            // 'stationName' : '0', 
            // 'rackTotCnt' : '1', 
            // 'parkingBikeTotCnt' : '2',
            // 'shared' : '3'
            // // 배열 내에서 찾기 : station에 keyword가 포함된 배열
            // bicycle은 배열이니까 stationName에 해당하는 배열 인덱스값
            const stationList = bicycleList.filter(bicycle => bicycle[Object.values(theadList).indexOf('stationName')].includes(keyword));
            
            console.log(stationList)
        
            // // 그 항목만 테이블에 렌더링
            setTable(stationList);
        }
        
        // thead 재설정
        const setThead = () => {
            const thead = document.querySelector('thead');
            thead.innerHTML = ''; //초기화
        
            const tr = document.createElement('tr');
            Object.keys(theadList).forEach(item => {
                const th = document.createElement('th');
                th.textContent = item;
                tr.appendChild(th);
            });
            thead.appendChild(tr);
        };
        
        
        
        // 테이블 추가
        // 객체가 아니라 리스트로 받아야겠다
        const setTable = (objList) => {
            currentArr = objList;
            // 'stationName' : '0', 
            // 'rackTotCnt' : '1', 
            // 'parkingBikeTotCnt' : '2',
            // 'shared' : '3'

            // 여기서 sortSel.val을 가져와서 그에 따라 정렬
            const selectedSort = sortSel.value;
            const selectedNum = Object.values(theadList).indexOf(selectedSort); // indexOf = 배열에서 selectedSort의 순서
            if(selectedSort == 'stationName')
                isAsc ? objList.sort((a, b) =>((a[selectedNum]).localeCompare(b[selectedNum]))) : objList.sort((a, b) =>((b[selectedNum]).localeCompare(a[selectedNum])));
            isAsc ? objList.sort((a, b) =>(a[selectedNum]-b[selectedNum])) : objList.sort((a, b) =>(b[selectedNum]-a[selectedNum]));
            
            tbody.innerHTML='';

            objList.forEach(trItem => {
                const tr = document.createElement('tr');

                trItem.forEach((tdItem, idx) => {
                    const td = document.createElement('td');
                    td.textContent = tdItem;
                    tr.appendChild(td);
                })
                tbody.appendChild(tr);
            })
        }
        
        setThead();
        setTable(bicycleList);
});
