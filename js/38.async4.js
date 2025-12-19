// generator 함수
//  - 함수의 실행을 나눠서 처리하는 함수


// 제네레이터 함수는 function 뒤에 *을 붙여서 선언
// function* generatorFunc() {
//     try {
//         console.log('yield 1 이전!');
//         yield 1;
//         console.log('yield 2 이전!');
//         yield 2;
//         console.log(abc); //ReferenceError (런타임)
//         console.log('yield 3 이전!');
//         yield 3;
//         console.log('yield 3 이후!');
        
//     } catch (err) {
//         console.log(err);
//     }
// }

// // 제네레이터 함수의 반환값은 제네레이터 객체
// const generator = generatorFunc();

// console.log(generator.next()); // { value: 1, done: false }
// console.log(generator.next()); // { value: 2, done: false }
// console.log(generator.next()); // { value: 3, done: false }
// // console.log(generator.return('반환')); // { value: '반환', done: true }, 함수 반환
// console.log(generator.throw('에러'));

// node 환경에서 fetch함수를 사용하려면 node-fetch를 설치해야 함
// cd js
// npm init -y
// 터미널(ctrl+`) : npm install node-fetch@2 --save-dev

// async / await
//   - ES8(2017)에 도입된 프라미스 기반의 비동기 처리를 쉽게 할 수 있도록 만든 키워드
//   - 일반적으로 함수 앞에 async 키워드를 붙여서 함수를 비동기 실행하고
//     비동기로 실행되는 함수 내에서 동기작업이 필요한 경우 동기작업 앞에 await 키워드를 사용


// node-fetch 라이브러리에서 fetch를 가져옴
// package.json에서 "type": "module"로 변경해야함
import fetch from 'node-fetch';

// 비동기로 처리되는 비동기 함수
// async function fetchTodo() {
//     // fetch(url)작업이 종료될때까지 기다렸다가 response 변수에 결과를 저장
//     const url = 'https://jsonplaceholder.typicode.com/todos/1';
//     const response = await fetch(url);
//     // response.json()작업이 종료될때까지 기다렸다가 todo 변수에 결과물 저장
//     const todo = await response.json();
//     console.log(todo);
// }

// fetchTodo();

// 실습
// 37.async3.js 실습 1,2번을 async/await 문법으로 변환



async function modifyTitle() {
    const url = 'https://jsonplaceholder.typicode.com/posts/20';
    const response = await fetch(url, { 
        method: 'PATCH', 
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify({ title: 'modified title' })
    });
    const post = await response.json();
    console.log(post);
}

modifyTitle();


// 실습2
// Promise를 이용해서 postId가 3인 post의 모든 댓글을 조회
async function getComments() {
    const response = await fetch('https://jsonplaceholder.typicode.com/comments');
    const comments = await response.json();
    const filteredComments = comments.filter(comment => comment.postId=='3');
    console.log(filteredComments);
}

getComments();