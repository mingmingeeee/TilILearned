// Template literals (Template strings) - 백틱 (`)

// 1. 백틱으로부터 벗어나려면 역슬래시를 사용
let result = `\`` === "`";

console.log(result);

// 2. 여러줄 입력 가능
console.log("문자열 첫번째 줄\n문자열 두번째 줄");
console.log(`문자열 첫번째 줄
문자열 두번재 줄`);

// 3. 문자열 보간 (String Interpolation)
const a = 5;
const b = 10;
console.log("Fifteen is " + (a + b) + " and\nnot " + (2 * a + b) + ".");
console.log(`Fifteen is ${a + b} and
not ${2 * a + b}.`);

// 3-1. CSS class 이름 동적으로 변경할 때 사용
const isLargeScreen = function () {
  return false;
};

const item = {
  isCollapsed: true,
};

const classes = `header ${
  isLargeScreen() ? "" : `icon-${item.isCollapsed ? "expander" : "collapser"}`
}`;

console.log(classes);

// 4. Tagged template
const person = "Mike";
const age = 28;

function myTag(strings, personExp, ageExp) {
  const str0 = strings[0];
  const str1 = strings[1];
  const str2 = strings[2];

  const ageStr = ageExp > 99 ? "centenarian" : "youngster";

  return `${str0}${personExp}${str1}${ageStr}${str2}`;
}

// myTage `` => 함수 쓰고 있는 거임
// strings: ["That", "is a", "."] 이렇게 배열로 넘어감
// 보간법(`${}`)으로 작성한 놈들은 그 뒤에 차례대로 들어감
const output = myTag`That ${person} is a ${age}.`;
console.log(output);
