const root = "";
const includePath = "/assets/include";

const includes = document.querySelectorAll(".include");
includes.forEach(function (include) {
  const html = include.getAttribute("data-html");
  fetch(`${includePath}/${html}`)
    .then(response => response.text())
    .then(data => {
      include.outerHTML = data;

      initHeader();
    });
});