const formulario = document.querySelector("form");

formulario.addEventListener("submit", function (event) {

    const senha = document.getElementById("senha");

    if (senha.value.length < 8) {
        event.preventDefault();

        alert("A senha deve ter pelo menos 8 caracteres.");

        senha.focus();
    }
});