// Destructuring assignment (분해 할당)
// 1. 배열의 값이나 객체의 속성을 하나의 변수로 풀어서 할당할 수 있는 JavaScript 표현식
let a, b, rest;

// 1. 배열의 값을 변수로 풀어서 할당
[a, b] = [10, 20];
console.log(a);
console.log(b);

// 2.
const obj = {
  message: "Hello",
  sayHello: function () {
    console.log(this.message);
  },
};

const { message, sayHello } = obj; // obj에서 message, sayHello 따로 빼올 수 있음
// 빼오면 this는 global 객체가 됨
console.log(message);
sayHello();
