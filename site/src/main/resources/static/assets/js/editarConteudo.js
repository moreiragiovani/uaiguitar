
const btnCriarAua = document.getElementById("criarAula");
const formContainerAula = document.getElementById("containerAulaForm");
btnCriarAua.addEventListener("click", function criarAula(){
    console.log("clicou");
    formContainerAula.classList.toggle("active");
});



