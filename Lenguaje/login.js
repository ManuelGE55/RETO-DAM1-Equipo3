// ARRAY DE USUARIOS
const usuarios = [
    {
        usuario: "robermach@gmail.com",
        password: "1234"
    },
    {
        usuario: "hfer@gmail.com",
        password: "4132"
    }
];


// ELEMENTOS HTML
const formulario = document.getElementById("formularioInicioSesion");

const usuarioInput = document.getElementById("usuario");
const passwordInput = document.getElementById("password");

const avisos = document.getElementById("avisos");



// =========================
// INICIAR SESIÓN
// =========================

formulario.addEventListener("submit", function (e) {

    e.preventDefault();
    const usuario = usuarioInput.value.trim();
    const password = passwordInput.value.trim();

    // BUSCAR USUARIO
    const encontrado = usuarios.find(function (u) {
        return (
            u.usuario === usuario &&
            u.password === password
        );
    });
    // MENSAJES
    if (encontrado) {
        avisos.textContent =
            "Inicio de sesión exitoso";
    }
    else {
        avisos.textContent =
            "La cuenta no existe";
    }
});

// =========================
// CREAR CUENTA
// =========================

botonCrear.addEventListener("click", function () {

    const usuario = usuarioInput.value.trim();
    const password = passwordInput.value.trim();

    // VALIDACIONES
    if (usuario === "" || password === "") {

        avisos.textContent =
            "Todos los campos son obligatorios";

        return;
    }

    if (usuario.length < 4) {

        avisos.textContent =
            "El usuario debe tener mínimo 4 caracteres";

        return;
    }

    if (password.length < 4) {

        avisos.textContent =
            "La contraseña debe tener mínimo 4 caracteres";

        return;
    }

    // COMPROBAR SI YA EXISTE
    const existe = usuarios.find(function (u) {

        return u.usuario === usuario;

    });

    if (existe) {

        avisos.textContent =
            "Ese usuario ya existe";

        return;
    }

    // CREAR USUARIO
    usuarios.push({

        usuario: usuario,
        password: password

    });

    avisos.textContent =
        "Cuenta creada correctamente";

    console.log(usuarios);

});