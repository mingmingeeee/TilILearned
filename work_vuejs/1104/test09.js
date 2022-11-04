// Spread syntax (...)

// 1. ... 연산자는 iterable(배열과 같은)을 잘게 쪼갭니다.
const cars1 = ["Saab", "Volvo", ..."BMW"]; // ... 붙이면 잘게 잘게 표현 가능
// ['B', 'M', 'W']
const cars2 = ["Fiat", "Toyota"];

const combined = [cars1, ...cars2]; // cars2에 찍음 =>
// 잘게 잘게 나와서 배열 바깥쪽으로 빠짐
console.log(combined);

// 2.
const numbers = [23, 55, 21, 87, 56];
let maxValue = Math.max(...numbers); // max 함수는 Function Rest Parameter 사용
console.log(maxValue);
