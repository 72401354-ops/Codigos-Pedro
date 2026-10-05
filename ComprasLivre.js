const produtos = [
    { id: 1, nome: "Fone Bluetooth", categoria: "Eletronicos", preco: 149.90, img: "https://picsum.photos/seed/fone/300/200" },
    { id: 2, nome: "Smartphone X", categoria: "Eletronicos", preco: 1899.00, img: "https://picsum.photos/seed/cell/300/200" },
    { id: 3, nome: "Liquidificador", categoria: "Casa", preco: 199.90, img: "https://picsum.photos/seed/liq/300/200" },
    { id: 4, nome: "Jogo de Panelas", categoria: "Casa", preco: 349.00, img: "https://picsum.photos/seed/panela/300/200" },
    { id: 5, nome: "Bola de Futebol", categoria: "Esportes", preco: 89.90, img: "https://picsum.photos/seed/bola/300/200" },
    { id: 6, nome: "Tênis de Corrida", categoria: "Esportes", preco: 279.90, img: "https://picsum.photos/seed/tenis/300/200" },
    { id: 7, nome: "Livro Best Seller", categoria: "Livros", preco: 49.90, img: "https://picsum.photos/seed/livro/300/200" },
    { id: 8, nome: "Coleção de Livros", categoria: "Livros", preco: 159.90, img: "https://picsum.photos/seed/colecao/300/200" }
];

let carrinho = [];
let categoriaAtual = "Todas";
let termoBusca = "";

function renderizarProdutos() {
    const lista = document.getElementById("listaProdutos");
    const semResultados = document.getElementById("semResultados");

    let filtrados = produtos.filter(p => {
        const matchCategoria = categoriaAtual === "Todas" || p.categoria === categoriaAtual;
        const matchBusca = p.nome.toLowerCase().includes(termoBusca.toLowerCase());
        return matchCategoria && matchBusca;
    });

    lista.innerHTML = "";

    if (filtrados.length === 0) {
        semResultados.style.display = "block";
        return;
    }
    semResultados.style.display = "none";

    filtrados.forEach(p => {
        const card = document.createElement("div");
        card.className = "produto-card";
        card.innerHTML = `
            <img src="${p.img}" alt="${p.nome}">
            <h3>${p.nome}</h3>
            <span class="categoria-tag">${p.categoria}</span>
            <span class="preco">R$ ${p.preco.toFixed(2).replace('.', ',')}</span>
            <button class="btn-comprar" onclick="adicionarAoCarrinho(${p.id})">Adicionar ao Carrinho</button>
        `;
        lista.appendChild(card);
    });
}

function filtrarProdutos() {
    termoBusca = document.getElementById("searchInput").value;
    renderizarProdutos();
}

function filtrarCategoria(event, categoria) {
    if (event) event.preventDefault();
    categoriaAtual = categoria;
    document.getElementById("sectionTitle").textContent =
        categoria === "Todas" ? "Todos os Produtos" : `Categoria: ${categoria}`;
    renderizarProdutos();
}

function adicionarAoCarrinho(id) {
    const produto = produtos.find(p => p.id === id);
    carrinho.push(produto);
    atualizarCarrinho();
}

function atualizarCarrinho() {
    const count = document.getElementById("cardCount");
    const total = document.getElementById("carrinhoTotal");
    const header = document.querySelector(".carrinho-header");
    const vazio = document.querySelector(".carrinho-vazio");

    count.textContent = carrinho.length;

    const soma = carrinho.reduce((acc, p) => acc + p.preco, 0);
    total.textContent = `R$ ${soma.toFixed(2).replace('.', ',')}`;

    // Remove itens antigos
    document.querySelectorAll(".carrinho-item").forEach(el => el.remove());

    if (carrinho.length === 0) {
        vazio.style.display = "block";
        return;
    }
    vazio.style.display = "none";

    carrinho.forEach((p, i) => {
        const item = document.createElement("div");
        item.className = "carrinho-item";
        item.innerHTML = `
            <span>${p.nome}</span>
            <span>R$ ${p.preco.toFixed(2).replace('.', ',')}
                <button onclick="removerDoCarrinho(${i})">✕</button>
            </span>
        `;
        header.appendChild(item);
    });
}

function removerDoCarrinho(index) {
    carrinho.splice(index, 1);
    atualizarCarrinho();
}

function toggleCarrinho(event) {
    event.preventDefault();
    document.getElementById("carrinho-painel").classList.toggle("aberto");
}

// Inicializa
document.addEventListener("DOMContentLoaded", () => {
    renderizarProdutos();
    atualizarCarrinho();
});