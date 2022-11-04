// Arrow Functions

// ES5
// var x = function (x, y) {
//   return x * y;
// };

// console.log(x(2, 3));

// ES6
// const x = (x, y) => x * y;
// console.log(x(2, 3));

// 1. return, 중괄호는 명시적으로 항상 작성하는 것이 좋음
// const x = (x, y) => {
//   return x * y;
// };
// console.log(x(2, 3));

// 2. 전통적인 익명 함수
// let x = function (a) {
//   return a + 100;
// };
// console.log(x(10));

// 2-1. 화살표 함수
// let x = (a) => {
//   return a + 100;
// };
// let y = x(10);
// console.log(y);

// 2-2. 중괄호, return 키워드 생략 가능 => 한 줄로 작성 가능할 때만
// { return ~ } 생략 가능
// let x = (a) => a + 100;
// let y = x(10);
// console.log(y);

// 2-3. 파라미터 괄호 생략 가능
// 파라미터가 하나만 존재할 때 생략 가능! 2개...3개...는 불가!
// let x = a => a + 100;
// let x = (a) => a + 100;
// let y = x(10);
// console.log(y);

// let x = (a, b) => a + b;
// let y = x(10, 20);
// console.log(y);

// 3-1. 화살표 함수를 전통적인 함수 사용법대로 사용 했을 때,
// 발생하는 문제들 살펴보기
// const obj = {
//   i: 10,
//   b: function () {
//     console.log("this b: " + this.i, this); // 함수 내부의 i
//     // { i: 10, b: [Function: b], c: [Function: c] } => obj 객체 자체를 의미함
//     // html에서 실행하면 => 웹상에서는 global객체가 "window"임
//    return 11;
//   },
//   c: () => {
//     console.log("this c: " + this.i, this); // undefined
//     // undefined {} => global 객체 => 제~일 최상단에 있는 global 객체에 접근함
//     return 12;
//   },
// };
// this.personName = "김싸피";
// console.log(obj.i); // 10
// console.log(obj.b()); // 11
// console.log(obj.c()); // 12

// 4-1. 클래스 선언
class C {
  // 기본 생성자 생략
  constructor() {}

  message = "안녕하세요"; // field 선언
  sayHello = () => {
    // class 내부 method에서 arrow function 사욯하면 this로 현 객체 가리키기 가능함
    // method 선언
    console.log(this.message);
  };
}

let c = new C();
c.sayHello();

// 6-1. arguments
const arguments = [1, 2, 3];
const arr = () => arguments[0];

let result1 = arr();
console.log(result1); // 1

// function foo(n, m) {
//   // arguments: 함수 내부에 있는 arguments => 파라미터 가리킴
//   // [0]: n, [1]: m
//   return arguments[0] + arguments[1] + n;
// }

// let result2 = foo(3, 4);
// console.log(result2);

// 함수 내부에서 함수 만들어서 리턴 => "일급 객체"
function foo(n) {
  const f = () => arguments[0] + n; // 화살표 함수에는 argument가 없고
  // 기존에 있는 정통 함수의 argument를 씀 ^0^...!!!
  return f(); // 함수 값 리턴
}

let result2 = foo(3);
console.log(result2);

// 6-2. 화살표 함수에서는 ...으로 대체 가능
function foo(n) {
  const f = (...args) => args[0] + n; // 가변 인자 => Function Rest Parameter (함수 나머지 파라미터)
  return f(10); // f 실행할 때 10 넘어감
  // => n: 1, args[0]: 10 => 10 + 1 = 11
}

let result = foo(1);
console.log(result);
