// Enhanced Object Literals
// 향상된 객체 리터럴을 활용해 코드를 작성하면 읽기 쉽고 간결한 객체를 만들 수 있습니다.

// 1. ShortHand properties (key 값, 변수 이름이 같으면 줄일 수 있음)
// const name = "김싸피";
// const age = 26;

// // const person = {
// //   name: name,
// //   age: age,
// // };
// // 를
// const person = {
//   name,
//   age,
// };
// // 로 !!!

// console.log(`이름: ${person.name}, 나이: ${person.age}`);

// 2. Method definitions (Concise method, Shorthand method names)
// const person = {
//   // sayHi: function () {
//   //   console.log("Hi");
//   // },
//   // 를
//   sayHi() {
//     console.log("Hi");
//   },
//   // 로 !!!
// };

// person.sayHi();

// 3. Computed property names
const dynamicKey = "name";
const index = 1;

const person = {
  [dynamicKey]: "김싸피",
  ["age" + index]: 26,
};

console.log(`이름: ${person.name}, 나이: ${person.age1}`);
