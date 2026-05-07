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
            B009: ["C00801", "C00802"],
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

const materiales = [
    {
        nombre: "Arduino UNO",
        cantidad: 5,
        armario: "GENERAL",
        balda: "B002",
        cajon: "C00201",
        descripcion: "Placas Arduino para prácticas"
    },
    {
        nombre: "Cable HDMI",
        cantidad: 10,
        armario: "GENERAL",
        balda: "B010",
        cajon: "C01001",
        descripcion: "Cables HDMI para monitores"
    },
    {
        nombre: "Raspberry Pi",
        cantidad: 3,
        armario: "GENERAL",
        balda: "B003",
        cajon: "C00301",
        descripcion: "Miniordenadores Raspberry"
    },
    {
        nombre: "Protoboard",
        cantidad: 8,
        armario: "GENERAL",
        balda: "B006",
        cajon: "C00601",
        descripcion: "Placas de pruebas electrónicas"
    },
    {
        nombre: "Resistencias",
        cantidad: 100,
        armario: "GENERAL",
        balda: "B008",
        cajon: "C00801",
        descripcion: "Resistencias varias"
    }
];

function verMapaGeneral() {
    document.getElementById("tituloMapa").innerHTML = "Vista general del armario";

    document.getElementById("mapaDinamico").innerHTML = `
        <div class="armario-general">

            <div class="fila">
                <div class="zona balda-larga" onclick="verBalda('GENERAL', 'B001')">B001</div>
            </div>

            <div class="fila">
                <div class="zona balda" onclick="verBalda('GENERAL', 'B002')">B002</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B003')">B003</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B004')">B004</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B005')">B005</div>
            </div>

            <div class="fila">
                <div class="zona balda" onclick="verBalda('GENERAL', 'B006')">B006</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B007')">B007</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B008')">B008</div>
                <div class="zona balda" onclick="verBalda('GENERAL', 'B009')">B009</div>
            </div>

            <div class="fila">
                <div class="zona armario-alto" onclick="verArmario('A1')">A1</div>

                <div style="flex: 3;">
                    <div class="fila">
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B010')">B010</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B013')">B013</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B016')">B016</div>
                    </div>

                    <div class="fila">
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B011')">B011</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B014')">B014</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B017')">B017</div>
                    </div>

                    <div class="fila">
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B012')">B012</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B015')">B015</div>
                        <div class="zona balda" onclick="verBalda('GENERAL', 'B018')">B018</div>
                    </div>

                    <div class="fila">
                        <div class="zona armario" onclick="verArmario('A2')">A2</div>
                        <div class="zona armario" onclick="verArmario('A3')">A3</div>
                        <div class="zona armario" onclick="verArmario('A4')">A4</div>
                    </div>
                </div>

                <div class="zona armario-alto" onclick="verArmario('A5')">A5</div>
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
            <div class="zona balda" onclick="verBalda('${codigoArmario}', '${baldas[i]}')">
                ${baldas[i]}
            </div>
        `;

        if (baldas[i + 1] !== undefined) {
            html += `
                <div class="zona balda" onclick="verBalda('${codigoArmario}', '${baldas[i + 1]}')">
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
                <div class="zona cajon" onclick="verCajon('${codigoArmario}', '${codigoBalda}', '${cajones[i]}')">
                    ${cajones[i]}
                </div>
            `;

            if (cajones[i + 1] !== undefined) {
                html += `
                    <div class="zona cajon" onclick="verCajon('${codigoArmario}', '${codigoBalda}', '${cajones[i + 1]}')">
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

function buscarMaterial(nombre) {
    let material = materiales.find(m => m.nombre === nombre);

    document.getElementById("info").innerHTML = `
        <h3>${material.nombre}</h3>
        <p><strong>Cantidad:</strong> ${material.cantidad}</p>
        <p><strong>Armario:</strong> ${material.armario}</p>
        <p><strong>Balda:</strong> ${material.balda}</p>
        <p><strong>Cajón:</strong> ${material.cajon}</p>
        <p><strong>Descripción:</strong> ${material.descripcion}</p>
    `;
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

verMapaGeneral();