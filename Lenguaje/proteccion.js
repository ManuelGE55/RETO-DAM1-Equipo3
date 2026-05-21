const usuario = sessionStorage.getItem("usuarioLogeado");

if (!usuario) {

    window.location.href = "LogIn.html";
}

function cerrarSesion() {

    sessionStorage.removeItem("usuarioLogeado");

    window.location.href = "LogIn.html";
}