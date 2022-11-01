// 초기화
function initHeader() {
  const loginUser = document.querySelector("#login-user");
  const btnShowLoginModal = document.querySelector("#btn-show-login-modal");

  const user = JSON.parse(sessionStorage.getItem("user"));
  console.log(user);
  if (user) {
    loginUser.querySelector("span").innerHTML = `${user.name}님 반갑습니다.`;
    loginUser.style.display = "block";
    btnShowLoginModal.style.display = "none";
  }
  else {
    loginUser.style.display = "none";
    btnShowLoginModal.style.display = "block";
  }
}

// 로그인
function login() {
  // 서버로 보낼 정보 작성
  let loginInfo = {
    id: document.querySelector("#userid").value,
    pass: document.querySelector("#userpass").value,
  };

  let config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(loginInfo),
  };

  fetch(`${root}/api/user/login`, config)
    .then(response => response.json())
    .then(data => {
      const loginUser = document.querySelector("#login-user");
      const btnShowLoginModal = document.querySelector("#btn-show-login-modal");
      loginUser.querySelector("span").innerHTML = `${data.name}님 반갑습니다.`;
      loginUser.style.display = "block";
      btnShowLoginModal.style.display = "none";

      sessionStorage.setItem("user", JSON.stringify(data));
    });
};

// 로그아웃
function logout() {
  fetch(`${root}/api/user/logout`)
    .then(response => {
      if (response.status == 200) {
        const loginUser = document.querySelector("#login-user");
        const btnShowLoginModal = document.querySelector("#btn-show-login-modal");
        loginUser.style.display = "none";
        btnShowLoginModal.style.display = "block";

        sessionStorage.removeItem("user");
        location.href = `${root}/`;
      }
    });
};