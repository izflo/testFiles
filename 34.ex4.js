// 이벤트 실습 4 : 폼 필드 유효성 검증 (form field validation)

// 전송버튼을 클릭하면...
// 1. 아이디와 비밀번호는 12자 이하, 필수 입력, 중간에 공백문자 불허
// 2. 성별은 필수 체크
// 3. 취미는 2개 이상 선택
// 4. 자기소개 필수 입력
// 조건을 모두 만족하면 '폼이 전송되었습니다!' alert
// 그렇지 않으면 해당 필드에 포커스



// 1. 아이디와 비밀번호는 12자 이하, 필수 입력, 중간에 공백문자 불허
// 정규표현식 -> /\S{,12}/
// idInput.test(/\S{,12}/) -> 표현에 맞으면 true 반환
const re12 = /^\S{1,12}$/;

// required인 부분에 validation check하고 focus시키기 위해
// document.querySelector('#submit').setAttribute('type', 'submit');

// 전송버튼을 클릭하면...
document.querySelector('#submit').addEventListener('click', e => {

// 필수 입력 -> setattribute 'required'
    const uid = document.querySelector("input[name=uid]");
    // uid.setAttribute('required', 'required');
    const upass = document.querySelector("input[name=upass]");
    // upass.setAttribute('required', 'required');
    const intro = document.querySelector("textarea[name=intro]");
    // upass.setAttribute('required', 'required');



    // 아이디 체크
    if(!re12.test(uid.value)) { // 정규표현식으로 유효성 검사
        alert('아이디는 공백 없이 12자 이하로 입력하세요!');
        uid.focus();
        return;
    }

    // // 비번 체크
    if(!re12.test(upass.value)) {
        alert('비밀번호는 공백 없이 12자 이하로 입력하세요!');
        upass.focus();
        return;
    }

    // 성별 체크
    // input type radio checked되어있는지 확인하기
    const gender = document.querySelectorAll('input[name=gender]:checked');
    // 값 돌면서 true 반환하는거 -> true가 있으면(has/include) -> 체크된 걸로 간주
    // some : 하나라도 체크가 되면 되니까
    // const checkGender = [...gender].some(e => e.checked);
    // *******************************:checked하면 필터링 됨
    if(gender.length == 0) {
        alert('성별을 선택해 주세요!');
        return;
    }


    // 취미 체크
    // input type checkbox 2개 이상 selected되어있는지 확인하기
    // filter -> 배열길이가 <2 -> alert('2개 이상 선택하세요!');
    // *******************************input[type=checkbox]:checked하면 필터링 됨
    const hobbies = document.querySelectorAll('input[type=checkbox]:checked');
    // const hobCnt = [...hobbies].filter(e => e.checked);
    if(hobbies.length < 2) {
        alert('취미는 2개 이상 선택하세요!');
        return;
    }

    // 자기소개 체크

    // /\S+/
    // if(intro.value.length < 1) {
    if(/\S+/.test(intro)) {
        alert('자기소개를 입력해 주세요!');
        intro.focus();
        return;
    }

    alert('폼이 전송되었습니다!');
});



// 2. 성별은 필수 체크
// 3. 취미는 2개 이상 선택
// 4. 자기소개 필수 입력
// 조건을 모두 만족하면 '폼이 전송되었습니다!' alert

// 그렇지 않으면 해당 필드에 포커스
// e.preventDefault()
// required항목에 표시되어 있지 않은 폼들 모아놓은 배열 순으로 focus();