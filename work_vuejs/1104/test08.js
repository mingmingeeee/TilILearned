// Function Rest Parameter (나머지 매개변수)
// 함수가 무한한 수의 파라미터를 배열로 처리할 수 있다.
function sum(...args) {
  let sum = 0;
  for (let arg of args) {
    sum += arg;
  }

  return sum;
}

let x = sum(4, 9, 16, 25, 29, 100, 66, 77);
console.log(x);
