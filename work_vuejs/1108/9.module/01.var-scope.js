console.log(this);
this.userId = "ssafy";

// var-scope.js가 module로 사용되고 있다면 'undefined'가 출력 됨.
console.log(window.userId);
