const root = "";
const includePath = "/assets/include"; // 서버 루트 경로 기준으로 작성

const includes = document.querySelectorAll(".include"); // include가 적용된 헤더 자체를 가져옴
includes.forEach(function (include) {
  const html = include.getAttribute("data-html"); // 파일 명 불러오기
  fetch(`${includePath}/${html}`) // get... include폴더 내부에 있는 header.html 파일 내용 가져오기...
    .then((response) => response.text()) // html 문서니까 ".text()"...
    .then((data) => {
      include.outerHTML = data; // 받아와서.... include 내용을 data로 대체하겠다는 의미...^^
      // outerHTML: 기존 내용 지우고 그 위에 덮어써서 복붙한 것처럼 됨

      switch (html) {
        case "header.html":
          initHeader(); // 초기화
          break;
      }
    });
});
