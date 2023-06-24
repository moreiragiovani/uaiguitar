
const svgAbrir = document.getElementById("svgAbrir");
const svgFechar = document.getElementById("svgFechar");
const sideBar = document.getElementById("sideBar");
const modulo = document.querySelectorAll(".nomeModulo");
const aulaUl =  document.querySelectorAll(".aula");
const active = "active";

const container = document.querySelectorAll(".containerCurso1");


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



function abrirM(){
    modulo.forEach((m, n) => { m.addEventListener("click",
        function (){
            const lista = container[n].querySelectorAll(".aula");
            lista.forEach((a) => {a.classList.toggle(active)});
        })});
}

abrirM();