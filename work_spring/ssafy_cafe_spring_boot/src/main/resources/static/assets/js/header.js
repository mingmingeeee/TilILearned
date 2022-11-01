// 초기화
function initHeader() {
  const loginUserTag = document.querySelector("#login-user");
  const btnShowLoginModal = document.querySelector("#btn-show-login-modal");

  const user = JSON.parse(sessionStorage.getItem("user"));

  if (user) {
    loginUserTag.querySelector("span").innerHTML = `${user.name}님 반갑습니다.`;
    loginUserTag.style.display = "block";
    btnShowLoginModal.style.display = "none";
  } else {
    loginUserTag.style.display = "none";
    btnShowLoginModal.style.display = "block";
  }
}

// 로그인
function login() {
  // 서버로 보낼 정보 작성
  const loginInfo = {
    id: document.querySelector("#userid").value,
    pass: document.querySelector("#userpass").value,
  };

  const config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginInfo),
  };

  fetch(`${root}/api/user/login`, config)
    .then((response) => response.json())
    .then((data) => {
      const loginUserTag = document.querySelector("#login-user");
      const btnShowLoginModal = document.querySelector("#btn-show-login-modal");

      loginUserTag.querySelector("span").innerHTML = `${data.name}님 반갑습니다.`;
      loginUserTag.style.display = "block";
      btnShowLoginModal.style.display = "none";

      sessionStorage.setItem("user", JSON.stringify(data));
    });
}

// 로그아웃
function logout() {
  fetch(`${root}/api/user/logout`).then((response) => {
    if (response.status == 200) {
      const loginUserTag = document.querySelector("#login-user");
      const btnShowLoginModal = document.querySelector("#btn-show-login-modal");

      loginUserTag.style.display = "none";
      btnShowLoginModal.style.display = "block";

      sessionStorage.removeItem("user");
      location.href = `${root}/`;
    }
  });
}
