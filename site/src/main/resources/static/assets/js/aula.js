class modulo{
    constructor(nomeModulo, aula) {
        this.nomeModulo = document.querySelectorAll(nomeModulo);
        this.aula = document.querySelectorAll(aula);
        this.activeItem = "active";
    }

    toggleAccordion(item){
        item.classList.toggle(this.actibeItem);
        item.nextElementSibling.classList.toggle(this.actibeItem);
    }

    addAccordionEvent(){
        this.nomeModulo.forEach((question) => {
            question.addEventListener("click", () => this.toggleAccordion(question))
        });
    }

    init(){
        if(this.nomeModulo.length){
            this.addAccordionEvent();
        }
        return modulo;
    }
}

const  accordion = new modulo(".nomeModulo", ".aula");
accordion.init();