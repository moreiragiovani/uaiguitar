const containerModulo = document.getElementById("containerForm");
const btnModulo = document.getElementById("criarModulo");

btnModulo.addEventListener("click", function (){
    containerModulo.classList.toggle("active");
})