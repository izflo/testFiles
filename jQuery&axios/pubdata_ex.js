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
        const bicycleList = bicycleObj.map(bcc => {
            return Object.values(theadList).map(val => bcc[val])
        });
        const tbody = document.querySelector('tbody');
        document.querySelector('h1').textContent = '서울시 공공자전거 실시간 대여정보'; // 테이블 제목 설정
        
        currentObj = {}; // 검색 후 정렬할 때 현재 정렬되어 있는 객체를 넘겨주기 위해

        
        document.querySelectorAll('button').forEach( btn => {
            btn.addEventListener('click', e => {
            switch (e.target.textContent) {
                case '검색':
                    searchTitle();
                    break;
        
                case 'ASC':
                    setTable(currentObj, 'asc');
                    break;
                
                case 'DESC':
                    setTable(currentObj, 'desc');
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
            const stationList = bicycleList.map(bicycle => bicycle.stationName.split('.')[1].trim());
                    
            // 배열 내에서 찾기 : station에 keyword가 포함된 배열
            const searchList = stationList.filter(station =>station.includes(keyword));
        
            // 그 항목만 테이블에 렌더링하기
            // searchList를 돌면서 이 값을 포함하는 객체를 리턴해야함
            const foundObj = bicycleList.filter(obj => obj.stationName.includes(String(keyword)));
            //객체를 배열로 만들어주는 함수, 그거 먼저 진행하고 ascend인지 desc인지 확인 후 settable
            setTable(foundObj);
            currentObj = foundObj; 
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
        const setTable = (objList, sort) => {
            // sort=='asc' ? objList.sort((a, b) =>((a).localeCompare(b))) : objList.sort((a, b) =>((b).localeCompare(a)));
            
            tbody.innerHTML='';      
            // bicycleList는 객체들을 모아놓은 배열
            // objList.forEach(bcl => {
            //     const tr = document.createElement('tr');
                
            //     Object.values(theadList).forEach(val => {
            //         if(val == 'stationName') {
            //             td.textContent = bcl[val].split('.')[1].trim();
            //         }
            //         else td.textContent= bcl[val];
            //         tr.appendChild(td);
            //     })
        
            //     tbody.appendChild(tr);
            // });

            objList.forEach(trItem => {
                const tr = document.createElement('tr');

                trItem.forEach((tdItem, idx) => {
                    const td = document.createElement('td');
                    if(idx==0) {
                        td.textContent = tdItem.split('.')[1].trim();
                    } else {
                        td.textContent = tdItem;
                    }
                    tr.appendChild(td);
                })

                tbody.appendChild(tr);

            })
                    
        }
        
        
        setThead();
        setTable(bicycleList);
        currentObj = bicycleList;

});