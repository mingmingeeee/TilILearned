// Default Parameter

function myFunction(x, y = 10) {
  // 두 번째 파라미터 값이 없거나 undefined이면, y는 10으로 넘어온다.
  return x + y;
}

let result = myFunction(5); // 15
console.log(result);
