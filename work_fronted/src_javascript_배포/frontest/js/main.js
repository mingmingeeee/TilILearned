let question = localStorage.getItem("question");
let contentLeftPollDiv = document.querySelector(".content-left-poll");
let contentLeftPollBtnDiv = document.querySelector(".content-left-poll-btn");

if (question) {
  //console.log("진행중인 투표있음.");
  let answers = localStorage.getItem("answers");
  let answerArr = JSON.parse(answers);
  console.log(answerArr);

  let poll = `
  <div class="poll-title">[당신의 선택]</div>
      <div class="poll-question">${question}</div>
      <div class="poll-answer">
        <ul>
        `;

  answerArr.forEach(function (answer) {
    poll += `<li><input type="radio" name="poll-answer" id="" /> ${answer}</li>`;
  });

  poll += `
        </ul>
      </div>
      <div class="poll-btn">
        <button id="btn-poll" class="button btn-primary">투표하기</button>
        <button class="button">이전결과보기</button>
      </div>
      <div class="poll-date">투표기간 : 2022.09.01 ~ 2022.09.30</div>
  `;
  contentLeftPollDiv.innerHTML = poll;
  contentLeftPollDiv.style.display = "";
  contentLeftPollBtnDiv.style.display = "none";
}
else {
  //console.log("진행중인 투표없음.");
  contentLeftPollDiv.style.display = "none";
  contentLeftPollBtnDiv.style.display = "";
}

document.querySelector("#btn-makepoll").addEventListener("click", function () {
  window.open('./makepoll.html', 'mkpoll', 'width=420,height=300');
});
