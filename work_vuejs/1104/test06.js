// Promise 객체
// Promise는 "Producing Code"와 "Consuming Code"를 연결하는 JavaScript 객체입니다.
// "Producing Code": 실행 시 시간이 걸리는 코드
// "Consuming Code": Producing Code의 결과를 기다리는 코드

// 콜백 함수 있음 myResolve(), myReject()
const myPromise = new Promise(function (myResolve, myReject) {
  // "Producing Code"
  // 아래 코드는 시간이 많이 걸린다고 가정
  let x = 0;
  for (let i = 1; i <= 10; i++) {
    x += i;
  }

  if (x == 55) {
    myResolve(x); // 성공 했을 때, 실행
  } else {
    myReject("fail"); // 실패 했을 때, 실행
  }
});

// "Consuming Code"
myPromise.then(
  function (value) {
    // myResolve
    /* 성공 시, 실행 */
    console.log(value);
  },
  function (error) {
    // myReject
    /* 실패 시, 실행 */
    console.log(error);
  }
);
