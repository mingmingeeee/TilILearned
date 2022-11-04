// For

// 1-1. For/Of
// const cars = ["헌땡", "기땡", "쌍땡"];
// let text = "";

// // Java의 향상된 For문과 유사
// for (let x of cars) {
//   text += x + " ";
// }

// console.log(text);

// let language = "JavaScript";
// let text = "";

// for (let x of language) {
//   text += x + " "; // J a v a S c r i p t
// }

// console.log(text);

// ES6 이전에 이미 존재했던 For문들

// 2-1. For/In 반복문
const person = {
  fname: "John",
  lname: "Doe",
  age: 25,
};

let text = "";
for (let x in person) {
  // text += x + " "; // 키를 가져옴
  text += person[x] + " "; // value들을 가져옴
}

console.log(text);

// 3-1. forEach() => 배열 순회하는 여러 방법 중 하나
const numbers = [45, 4, 9, 16, 25];
let txt = "";
let myFunction = function (value, index, array) {
  txt += value + " ";
  console.log(`인덱스: ${index}, 배열: ${array}`);
};
numbers.forEach(myFunction);
console.log(txt);
