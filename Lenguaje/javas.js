const estructura = {
    GENERAL: {
        nombre: "Vista general",
        baldas: {
            B001: [],
            B002: ["C00201", "C00202", "C00203", "C00204", "C00205", "C00206"],
            B003: ["C00301"],
            B004: [],
            B005: [],
            B006: ["C00601", "C00602", "C00603", "C00604", "C00605", "C00606"],
            B007: ["C00701"],
            B008: ["C00801", "C00802", "C00803", "C00804", "C00805"],
            B009: ["C00901", "C00902"],
            B010: ["C01001", "C01002", "C01003", "C01004"],
            B011: [],
            B012: [],
            B013: ["C01301", "C01302"],
            B014: ["C01401", "C01402"],
            B015: [],
            B016: ["C01601", "C01602", "C01603", "C01604", "C01605"],
            B017: ["C01701"],
            B018: ["C01801", "C01802"]
        }
    },

    A1: {
        nombre: "Armario A1",
        baldas: {
            B101: [],
            B102: [],
            B103: [],
            B104: [],
            B105: [],
            B106: [],
            B107: [],
            B108: [],
            B109: [],
            B110: [],
            B111: [],
            B112: []
        }
    },

    A2: {
        nombre: "Armario A2",
        baldas: {
            B201: [],
            B202: []
        }
    },

    A3: {
        nombre: "Armario A3",
        baldas: {
            B301: [],
            B302: []
        }
    },

    A4: {
        nombre: "Armario A4",
        baldas: {
            B401: [],
            B402: []
        }
    },

    A5: {
        nombre: "Armario A5",
        baldas: {
            B501: [],
            B502: [],
            B503: [],
            B504: [],
            B505: [],
            B506: [],
            B507: [],
            B508: [],
            B509: [],
            B510: [],
            B511: [],
            B512: []
        }
    }
};

let materiales = [];

function cargarMaterialesJSON() {
    fetch("inventario.json")
        .then(respuesta => respuesta.json())
        .then(datos => {
            materiales = datos;
            cargarListaComponentes();
            verMapaGeneral();
        })
        .catch(error => {
            console.error("Error al cargar inventario.json:", error);

            materiales = [];
            cargarListaComponentes();
            verMapaGeneral();
        });
}

function cargarListaComponentes() {
    let lista = document.getElementById("listaComponentes");

    console.log("Lista encontrada:", lista);
    console.log("Materiales cargados:", materiales);

    if (lista == null) {
        console.error("No existe el elemento con id listaComponentes en el HTML");
        return;
    }

    lista.innerHTML = "";

    materiales.forEach(material => {
        lista.innerHTML += `
            <li>
                <button onclick="buscarMaterial('${material.nombre}')">
                    ${material.nombre}
                </button>
            </li>
        `;
    });
}

function verMapaGeneral() {
    document.getElementById("tituloMapa").innerHTML = "Vista general del armario";

    document.getElementById("mapaDinamico").innerHTML = `
        <div class="armario-general">

            <div class="fila">
                <div id="B001" class="zona balda-larga" onclick="verBalda('GENERAL', 'B001')">B001</div>
            </div>

            <div class="fila">
                <div id="B002" class="zona balda" onclick="verBalda('GENERAL', 'B002')">B002</div>
                <div id="B003" class="zona balda" onclick="verBalda('GENERAL', 'B003')">B003</div>
                <div id="B004" class="zona balda" onclick="verBalda('GENERAL', 'B004')">B004</div>
                <div id="B005" class="zona balda" onclick="verBalda('GENERAL', 'B005')">B005</div>
            </div>

            <div class="fila">
                <div id="B006" class="zona balda" onclick="verBalda('GENERAL', 'B006')">B006</div>
                <div id="B007" class="zona balda" onclick="verBalda('GENERAL', 'B007')">B007</div>
                <div id="B008" class="zona balda" onclick="verBalda('GENERAL', 'B008')">B008</div>
                <div id="B009" class="zona balda" onclick="verBalda('GENERAL', 'B009')">B009</div>
            </div>

            <div class="fila">
                <div id="A1" class="zona armario-alto" onclick="verArmario('A1')">A1</div>

                <div style="flex: 3;">
                    <div class="fila">
                        <div id="B010" class="zona balda" onclick="verBalda('GENERAL', 'B010')">B010</div>
                        <div id="B013" class="zona balda" onclick="verBalda('GENERAL', 'B013')">B013</div>
                        <div id="B016" class="zona balda" onclick="verBalda('GENERAL', 'B016')">B016</div>
                    </div>

                    <div class="fila">
                        <div id="B011" class="zona balda" onclick="verBalda('GENERAL', 'B011')">B011</div>
                        <div id="B014" class="zona balda" onclick="verBalda('GENERAL', 'B014')">B014</div>
                        <div id="B017" class="zona balda" onclick="verBalda('GENERAL', 'B017')">B017</div>
                    </div>

                    <div class="fila">
                        <div id="B012" class="zona balda" onclick="verBalda('GENERAL', 'B012')">B012</div>
                        <div id="B015" class="zona balda" onclick="verBalda('GENERAL', 'B015')">B015</div>
                        <div id="B018" class="zona balda" onclick="verBalda('GENERAL', 'B018')">B018</div>
                    </div>

                    <div class="fila">
                        <div id="A2" class="zona armario" onclick="verArmario('A2')">A2</div>
                        <div id="A3" class="zona armario" onclick="verArmario('A3')">A3</div>
                        <div id="A4" class="zona armario" onclick="verArmario('A4')">A4</div>
                    </div>
                </div>

                <div id="A5" class="zona armario-alto" onclick="verArmario('A5')">A5</div>
            </div>

        </div>
    `;
}

function verArmario(codigoArmario) {
    let armario = estructura[codigoArmario];
    let baldas = Object.keys(armario.baldas);

    document.getElementById("tituloMapa").innerHTML = `Interior de ${codigoArmario}`;

    let html = `
        <div class="interior-armario">
            <div class="ruta">
                <button onclick="verMapaGeneral()">← Volver al mapa general</button>
                ${codigoArmario}
            </div>
    `;

    for (let i = 0; i < baldas.length; i += 2) {
        html += `<div class="fila">`;

        html += `
            <div id="${baldas[i]}" class="zona balda" onclick="verBalda('${codigoArmario}', '${baldas[i]}')">
                ${baldas[i]}
            </div>
        `;

        if (baldas[i + 1] !== undefined) {
            html += `
                <div id="${baldas[i + 1]}" class="zona balda" onclick="verBalda('${codigoArmario}', '${baldas[i + 1]}')">
                    ${baldas[i + 1]}
                </div>
            `;
        }

        html += `</div>`;
    }

    html += `</div>`;

    document.getElementById("mapaDinamico").innerHTML = html;

    mostrarMaterialesArmario(codigoArmario);
}

function verBalda(codigoArmario, codigoBalda) {
    let cajones = estructura[codigoArmario].baldas[codigoBalda];

    document.getElementById("tituloMapa").innerHTML = `Interior de ${codigoBalda}`;

    let html = `
        <div class="interior-armario">
            <div class="ruta">
                <p><strong>${codigoArmario} > ${codigoBalda}</strong></p>
                <button onclick="verMapaGeneral()">← Mapa general</button>
            </div>
    `;

    if (cajones.length > 0) {
        for (let i = 0; i < cajones.length; i += 2) {
            html += `<div class="fila">`;

            html += `
                <div id="${cajones[i]}" class="zona cajon" onclick="verCajon('${codigoArmario}', '${codigoBalda}', '${cajones[i]}')">
                    ${cajones[i]}
                </div>
            `;

            if (cajones[i + 1] !== undefined) {
                html += `
                   <div id="${cajones[i + 1]}" class="zona cajon" onclick="verCajon('${codigoArmario}', '${codigoBalda}', '${cajones[i + 1]}')">
                        ${cajones[i + 1]}
                    </div>
                `;
            }

            html += `</div>`;
        }
    }

    html += `</div>`;

    document.getElementById("mapaDinamico").innerHTML = html;

    mostrarMaterialesBalda(codigoArmario, codigoBalda);
}

function verCajon(codigoArmario, codigoBalda, codigoCajon) {
    
    marcarZona(codigoCajon);

    let encontrados = materiales.filter(m =>
        m.armario === codigoArmario &&
        m.balda === codigoBalda &&
        m.cajon === codigoCajon
    );

    document.getElementById("info").innerHTML = `
        <h3>${codigoArmario} > ${codigoBalda} > ${codigoCajon}</h3>
        ${crearTextoMateriales(encontrados)}
    `;
}

function limpiarMarcado() {
    let zonas = document.getElementsByClassName("zona");

    for (let i = 0; i < zonas.length; i++) {
        zonas[i].classList.remove("zona-activa");
    }
}

function marcarZona(codigo) {
    limpiarMarcado();

    let zona = document.getElementById(codigo);

    if (zona !== null) {
        zona.classList.add("zona-activa");
    }
}

function buscarMaterial(nombre) {

    let material = materiales.find(m => m.nombre === nombre);

    if (material === undefined) {

        verMapaGeneral();
        limpiarMarcado();

        document.getElementById("info").innerHTML = `
            <h3>${nombre}</h3>
            <p>Este componente todavía no tiene ubicación registrada.</p>
        `;

        return;
    }

    if (material.cajon !== "") {

        verBalda(material.armario, material.balda);

        setTimeout(() => {

            marcarZona(material.cajon);

        }, 50);

    } else {

        if (material.armario === "GENERAL") {
            verMapaGeneral();
        } else {
            verArmario(material.armario);
        }

        setTimeout(() => {

            marcarZona(material.balda);

        }, 50);
    }

    setTimeout(() => {

        document.getElementById("info").innerHTML = `
            <h3>${material.nombre}</h3>

            <p><strong>Cantidad:</strong> ${material.cantidad}</p>

            <p><strong>Ubicación:</strong>
                ${material.armario}
                >
                ${material.balda}
                ${material.cajon !== "" ? "> " + material.cajon : ""}
            </p>

            <p><strong>Descripción:</strong> ${material.descripcion}</p>
        `;

    }, 60);
}

function mostrarMaterialesArmario(codigoArmario) {
    let encontrados = materiales.filter(m => m.armario === codigoArmario);

    document.getElementById("info").innerHTML = `
        <h3>Contenido general de ${codigoArmario}</h3>
        ${crearTextoMateriales(encontrados)}
    `;
}

function mostrarMaterialesBalda(codigoArmario, codigoBalda) {
    let encontrados = materiales.filter(m =>
        m.armario === codigoArmario &&
        m.balda === codigoBalda
    );

    document.getElementById("info").innerHTML = `
        <h3>Contenido de ${codigoArmario} > ${codigoBalda}</h3>
        ${crearTextoMateriales(encontrados)}
    `;
}

function crearTextoMateriales(lista) {
    if (lista.length === 0) {
        return `<p>No hay materiales registrados aquí.</p>`;
    }

    let texto = "";

    lista.forEach(m => {
        texto += `
            <div>
                <p>
                    <strong>${m.nombre}</strong><br>
                    Cantidad: ${m.cantidad}<br>
                    Ubicación: ${m.armario} > ${m.balda} > ${m.cajon}<br>
                    ${m.descripcion}
                </p>
                <hr>
            </div>
        `;
    });

    return texto;
}

cargarMaterialesJSON();