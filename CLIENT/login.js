function fazerLogin() {
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    var grupo = document.getElementById("grupo").value.toUpperCase();

    var data = {
        email: email,
        senha: senha,
        grupo: grupo
    };

    fetch("http://localhost:8080/usuarios/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(function (response, data) {
        if (response.status === 200) {
            return response.json().then(function (userData) {
                lidarComLoginBemSucedido(userData);
            });
        } else if (response.status === 401) {
            exibirMensagemDeErro("Usuário não encontrado. Verifique as informações.  Caso não esteja cadastrado ou ativo peça a um administrador ativo!");
            throw new Error("Erro na solicitação.");
        }
    })
    .catch(function (error) {
        exibirMensagemDeErro("Erro no login: " + error.message);
    });
}

function exibirMensagemDeErro(mensagem) {
    var mensagemErro = document.getElementById("mensagem-erro");
    mensagemErro.textContent = mensagem;
    alert(mensagem);
}
function lidarComLoginBemSucedido(userData) {
    console.log("Usuário logado com sucesso:", userData);
    // Faça o que for necessário após o login bem-sucedido, como redirecionar o usuário
    // window.location.href = "seu_destino.html";
}

// Chame a função fazerLogin quando o formulário for submetido
var form = document.getElementById("login-form");
form.addEventListener("submit", function (event) {
    event.preventDefault();
    fazerLogin();
});
