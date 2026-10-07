const produtos = document.querySelectorAll(".produto-checkbox");
const total = document.getElementById("totalCompra");
const valorTotalInput = document.getElementById("valorTotal");
const produtosSelecionados = document.getElementById("produtosSelecionados");
const btnComprar = document.getElementById("btnComprar");

function atualizarTotal() {

    let valorTotal = 0;

    produtosSelecionados.innerHTML = "";

    produtos.forEach(function (produto) {

        if (produto.checked) {

            valorTotal += Number(produto.dataset.valor);

            const input = document.createElement("input");

            input.type = "hidden";
            input.name = "produtos";
            input.value = produto.value;

            produtosSelecionados.appendChild(input);
        }

    });

    total.textContent = valorTotal.toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });

    valorTotalInput.value = valorTotal;
}

produtos.forEach(function (produto) {
    produto.addEventListener("change", atualizarTotal);
});

btnComprar.addEventListener("click", function (event) {

    let valorTotal = 0;

    produtos.forEach(function (produto) {

        if (produto.checked) {
            valorTotal += Number(produto.dataset.valor);
        }

    });

    if (valorTotal === 0) {
        event.preventDefault();
        alert("Selecione pelo menos um produto.");
    }
});