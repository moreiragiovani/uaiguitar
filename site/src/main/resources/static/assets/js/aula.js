
const svgAbrir = document.getElementById("svgAbrir");
const svgAbrirMenu = document.getElementById("svgAbrirMenu");
const svgFechar = document.getElementById("svgFechar");
const svgFecharMenu = document.getElementById("svgFecharMenu");
const sideBar = document.getElementById("sideBar");
const naveBar = document.getElementById("naveBar");
const modulo = document.querySelectorAll(".nomeModulo");
const aulaUl =  document.querySelectorAll(".aula");
const active = "active";

const container = document.querySelectorAll(".containerCurso1");

function menuSvg(){
    svgAbrirMenu.addEventListener("click", function (){
        naveBar.style.display = "flex";
        svgAbrirMenu.style.display = "none";
        svgFecharMenu.style.display = "block";
    });
}

function menuSvgFehar(){
    svgFecharMenu.addEventListener("click", function (){
        naveBar.style.display = "none";
        svgAbrirMenu.style.display = "block";
        svgFecharMenu.style.display = "none";
    });
}

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
menuSvg();
menuSvgFehar();
