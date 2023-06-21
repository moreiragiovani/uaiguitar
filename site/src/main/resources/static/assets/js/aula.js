
const svgAbrir = document.getElementById("svgAbrir");
const svgFechar = document.getElementById("svgFechar");
const sideBar = document.getElementById("sideBar");
const active = "active";

const abrirModulo = document.querySelectorAll(".nomeModulo");


function clicar(){
    sideBar.classList.toggle(active);
    svgFechar.style.display = "block";
    svgAbrir.style.display = "none";
}

function clicarFechar(){
    sideBar.classList.toggle(active);
    svgFechar.style.display = "none";
    svgAbrir.style.display = "block";
}
svgAbrir.addEventListener("click", clicar);
svgFechar.addEventListener("click", clicarFechar);