function cargarXML(callback) {
    fetch("inventario.xml")
        .then(respuesta => respuesta.text())
        .then(texto => {
            let parser = new DOMParser();
            let xml = parser.parseFromString(texto, "text/xml");
            callback(xml);
        });
}

function obtenerTexto(material, etiqueta) {
    return material.getElementsByTagName(etiqueta)[0].textContent;
}

function generarCSVDesdeXML() {
    cargarXML(function(xml) {
        let materiales = xml.getElementsByTagName("material");

        let csv = "Nombre;Cantidad;Armario;Balda;Cajon;Descripcion\n";

        for (let i = 0; i < materiales.length; i++) {
            let m = materiales[i];

            csv += obtenerTexto(m, "nombre") + ";";
            csv += obtenerTexto(m, "cantidad") + ";";
            csv += obtenerTexto(m, "armario") + ";";
            csv += obtenerTexto(m, "balda") + ";";
            csv += obtenerTexto(m, "cajon") + ";";
            csv += obtenerTexto(m, "descripcion") + "\n";
        }

        let archivo = new Blob([csv], { type: "text/csv" });
        let enlace = document.createElement("a");

        enlace.href = URL.createObjectURL(archivo);
        enlace.download = "informe_inventario.csv";
        enlace.click();
    });
}

function mostrarInventarioDesdeXML() {
    cargarXML(function(xml) {
        let materiales = xml.getElementsByTagName("material");

        let html = `
            <table class="tabla-inventario">
                <tr>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Armario</th>
                    <th>Balda</th>
                    <th>Cajón</th>
                    <th>Descripción</th>
                </tr>
        `;

        for (let i = 0; i < materiales.length; i++) {
            let m = materiales[i];

            html += `
                <tr>
                    <td>${obtenerTexto(m, "nombre")}</td>
                    <td>${obtenerTexto(m, "cantidad")}</td>
                    <td>${obtenerTexto(m, "armario")}</td>
                    <td>${obtenerTexto(m, "balda")}</td>
                    <td>${obtenerTexto(m, "cajon")}</td>
                    <td>${obtenerTexto(m, "descripcion")}</td>
                </tr>
            `;
        }

        html += `</table>`;

        document.getElementById("inventarioHTML").innerHTML = html;
    });
}

function ocultarInventario() {
    document.getElementById("inventarioHTML").innerHTML = "";
}