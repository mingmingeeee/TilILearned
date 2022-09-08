window.onload = function () {
    document.querySelector("#btn-add").addEventListener("click", function () {
      let listDiv = document.querySelector("#poll-answer-list");
  
      let divEl = document.createElement("div");
      divEl.setAttribute("class", "poll-answer-item");
  
      let inputEl = document.createElement("input");
      inputEl.setAttribute("type", "text");
      inputEl.setAttribute("name", "answer");
  
      let buttonEl = document.createElement("button");
      buttonEl.setAttribute("type", "button");
      buttonEl.setAttribute("class", "button");
      buttonEl.appendChild(document.createTextNode("삭제"));
  
      buttonEl.addEventListener("click", function () {
        let parentEl = this.parentElement;
        listDiv.removeChild(parentEl);
      });
  
      divEl.appendChild(inputEl);
      divEl.appendChild(buttonEl);
  
      listDiv.appendChild(divEl);
    });
  
    document.querySelector("#btn-make").addEventListener("click", function () {
      let question = document.querySelector("#question").value;
      //console.log(question);
      if (!question) {
        alert("질문은 반드시 입력!!!!");
        return;
      }
  
      let answers = document.querySelectorAll("[name=answer]");
      //console.log(answers);
      for (let answer of answers) {
        if (!answer.value) {
          alert("답변을 입력!!!");
          return;
        }
      }
  
      let answerArr = [];
      for (let answer of answers) {
        answerArr.push(answer.value);  // ["서울", ... "부물경"]
      }
  
      localStorage.setItem("question", question);
      localStorage.setItem("answers", JSON.stringify(answerArr));
  
      if (confirm("투표 생성 하시겠습니까??")) {
        opener.location.reload();
        self.close();
      }
    });
  };
  
  