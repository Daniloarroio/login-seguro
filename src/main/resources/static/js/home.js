const produtos = document.querySelectorAll(".produto-checkbox");
const total = document.getElementById("total");
const btnComprar = document.getElementById("btnComprar");

function atualizarTotal() {

    let valorTotal = 0;

    produtos.forEach(function (produto) {

        if (produto.checked) {
            valorTotal += Number(produto.dataset.preco);
        }

    });

    total.textContent = valorTotal.toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });
}

produtos.forEach(function (produto) {
    produto.addEventListener("change", atualizarTotal);
});

btnComprar.addEventListener("click", function () {

    let valorTotal = 0;

    produtos.forEach(function (produto) {

        if (produto.checked) {
            valorTotal += Number(produto.dataset.preco);
        }

    });

    if (valorTotal === 0) {
        alert("Selecione pelo menos um produto.");
        return;
    }

    alert(
        "Compra realizada com sucesso!\nTotal: " +
        valorTotal.toLocaleString("pt-BR", {
            style: "currency",
            currency: "BRL"
        })
    );
});