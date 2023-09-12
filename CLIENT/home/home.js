document.addEventListener("DOMContentLoaded", function () {
    // Esconde todos os conteúdos das abas e exibe apenas a imagem no início
    var tabs = document.querySelectorAll(".aba");
    tabs.forEach(function (tab) {
        tab.style.display = "none";
    });

    document.querySelector(".image-container").style.display = "block";
});

function showTab(tabId) {
    // Esconde todos os conteúdos das abas
    var tabs = document.querySelectorAll(".aba");
    tabs.forEach(function (tab) {
        tab.style.display = "none";
    });

    // Exibe o conteúdo da aba selecionada
    var selectedTab = document.getElementById(tabId);
    if (selectedTab) {
        selectedTab.style.display = "block";
        
        // Verifica se a aba é "Usuários" e decide se deve exibir o campo de busca
        if (tabId === "usuarios") {
            document.querySelector(".search").style.display = "block";
        } else {
            document.querySelector(".search").style.display = "none";
        }
        
        // Esconde a imagem quando uma aba for selecionada
        document.querySelector(".image-container").style.display = "none";
    }
    listarUsuarios();
}

function searchUsers() {
    // Obtém o valor do campo de busca
    var searchValue = document.getElementById("searchInput").value.toLowerCase();
    
    // Obtém todas as linhas da tabela de usuários
    var rows = document.querySelectorAll(".user-table tbody tr");

    // Itera sobre as linhas e exibe ou oculta com base na pesquisa
    rows.forEach(function (row) {
        var nome = row.querySelector("td:first-child").textContent.toLowerCase();
        
        if (nome.includes(searchValue)) {
            row.style.display = "table-row"; // Exibe a linha
        } else {
            row.style.display = "none"; // Oculta a linha
        }
    });
}

function alterarUsuario(nome, email, status, grupo) {
    // Redireciona para a página de alteração de usuário com os parâmetros necessários
    window.location.href = `alterar-usuario.html?nome=${nome}&email=${email}&status=${status}&grupo=${grupo}`;
}

function goToHome() {
    // Redireciona para a página inicial ou realiza a ação desejada ao clicar no nome da loja
    // Por exemplo, você pode redirecionar para a página inicial:
    window.location.href = "index.html";
}

function listarUsuarios() {
    const apiUrl = 'http://localhost:8080/usuarios';

    // Elemento <tbody> da tabela onde os dados serão inseridos
    const tbody = document.querySelector('.user-table tbody');

    // Limpa o conteúdo existente na tabela
    tbody.innerHTML = '';

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar dados dos usuários');
            }
            return response.json();
        })
        .then(data => {
            // Itera sobre os dados dos usuários e cria linhas na tabela
            data.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.nome}</td>
                    <td>${user.email}</td>
                    <td>${user.status}</td>
                    <td>${user.grupo}</td>
                    <td><button onclick="alterarUsuario('${user.nome}', '${user.email}', '${user.status}', '${user.grupo}')">Alterar Usuário</button></td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
        });
}

