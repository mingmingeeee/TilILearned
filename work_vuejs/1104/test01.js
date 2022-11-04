// let & const

// 1-1.
// var x = 10;
// console.log(x); // 10
// {
//   let x = 2;
//   console.log(x); // 2
// }
// console.log(x); // 10

// 1-2.
// 자바 스크립트 엔진이 읽어들일 때 => "var" 변수들은 최상단으로 올려줌 (변수 호이스팅)
// var x; 가 제일 위에 올라 가있음
// x = 10;
// x = 2;
// 이렇게 되는 거로 생각하면 됨 (똑같은 변수 x를 계속 쓰는 것)
// var x = 10; // 호이스팅
// console.log(x); // 10
// {
//   var x = 2; // 호이스팅
//   console.log(x); // 2
// }
// console.log(x); // 2

// 1-3.
// let: ES6에 나온 키워드! => 자기가 속한 scope내에서만 변수 쓸 수 있음
// let x = 10;
// console.log(x); // 10
// {
//   let x = 2;
//   console.log(x); // 2
// }
// console.log(x); // 10

// 2-1.
console.log(userName);
let userName;
