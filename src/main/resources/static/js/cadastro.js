 const formulario = document.querySelector("form");

const senha = document.getElementById("senha");
const confirmarSenha = document.getElementById("confirmarSenha");

formulario.addEventListener("submit", function (event) {

    if (senha.value !== confirmarSenha.value) {
        event.preventDefault();

        alert("As senhas não coincidem.");

        confirmarSenha.focus();

        return;
    }

    if (senha.value.length < 8) {
        event.preventDefault();

        alert("A senha deve ter pelo menos 8 caracteres.");

        senha.focus();

        return;
    }
});