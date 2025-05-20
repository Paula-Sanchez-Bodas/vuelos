document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const usuario = params.get("usuario");

    // Estamos en reservas.html si existe el span del nombre
    const spanUsuario = document.getElementById("nombre-usuario");
    const reservasSection = document.querySelector(".reservas-section");

    if (spanUsuario && usuario) {
        spanUsuario.textContent = usuario;

        fetch(`http://localhost:8080/reservas?usuario=${usuario}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error en la respuesta: ${response.status}`);
                }
                return response.json();
            })
            .then(reservas => {
                if (reservas.length === 0) {
                    const msg = document.createElement("p");
                    msg.textContent = "No hay reservas para este usuario.";
                    reservasSection.appendChild(msg);
                } else {
                    reservas.forEach(reserva => {
                        const card = document.createElement("div");
                        card.classList.add("reserva-card");
                        card.innerHTML = `
              <p><strong>Usuario:</strong> ${reserva.usuario}</p>
              <p><strong>Origen:</strong> ${reserva.origen}</p>
              <p><strong>Destino:</strong> ${reserva.destino}</p>
              <p><strong>Fecha Salida:</strong> ${reserva.fechaSalida}</p>
              <p><strong>Fecha Regreso:</strong> ${reserva.fechaRegreso}</p>
              <p><strong>Precio:</strong> ${reserva.precio} €</p>
            `;
                        reservasSection.appendChild(card);
                    });
                }
            })
            .catch(error => {
                console.error("Error al cargar reservas:", error);
                reservasSection.innerHTML += `<p style="color:#ffffff;">No se pudieron cargar las reservas. Inténtalo más tarde.</p>`;
            });
    }

});


document.addEventListener("DOMContentLoaded", () => {
    const airportForm = document.getElementById("airport-form");
    const airportResults = document.querySelector(".airport-results");

    airportForm.addEventListener("submit", function (e) {
        e.preventDefault(); // evitar recargar la página
        const ciudad = document.getElementById("ciudad").value.trim();

        if (!ciudad) return;

        fetch(`http://localhost:8080/aeropuertos?nombreCiudad=${ciudad}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error al obtener aeropuertos");
                }
                return response.json();
            })
            .then(data => {
                airportResults.innerHTML = ""; // limpiar resultados anteriores

                if (data.length === 0) {
                    airportResults.innerHTML = "<p>No se encontraron aeropuertos para esa ciudad.</p>";
                    return;
                }

                data.forEach(aeropuerto => {
                    const card = document.createElement("div");
                    card.classList.add("airport-card");
                    card.innerHTML = `
            <p><strong>Aeropuerto:</strong> ${aeropuerto.codigoAeropuerto}</p>
            <p><strong>País:</strong> ${aeropuerto.pais}</p>
          `;
                    airportResults.appendChild(card);
                });
            })
            .catch(error => {
                console.error("Error:", error);
                airportResults.innerHTML = "<p>Error al cargar los aeropuertos.</p>";
            });
    });
});

document.getElementById("search-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que recargue la página

    const origen = document.getElementById("origen").value;
    const destino = document.getElementById("destino").value;
    const fechaIda = document.getElementById("fechaIda").value;
    const fechaVuelta = document.getElementById("fechaVuelta").value;

    let url = `http://localhost:8080/vuelos?origen=${encodeURIComponent(origen)}&destino=${encodeURIComponent(destino)}&fechaIda=${fechaIda}`;
    if(fechaVuelta) {
        url += `&fechaVuelta=${fechaVuelta}`;
    }

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Error al obtener vuelos");
            return response.json();
        })
        .then(data => {
            mostrarVuelos(data);
        })
        .catch(error => {
            console.error(error);
            alert("Error al cargar vuelos.");
        });
});

function mostrarVuelos(data) {
    // Se crea dinámicamente el HTML para mostrar los vuelos

    const contenedor = document.getElementById("resultados-vuelos");
    contenedor.innerHTML = ""; // Limpia resultados previos

    if(!data.listaOfertas || data.listaOfertas.length === 0) {
        contenedor.innerHTML = "<p>No se encontraron vuelos.</p>";
        return;
    }

    data.listaOfertas.forEach(oferta => {
        const vueloCard = document.createElement("div");
        vueloCard.classList.add("vuelo-card");

        vueloCard.innerHTML = `
      <p><strong>Origen:</strong> ${oferta.itinerarioIda.listaSeguimientos[0].origen}</p>
      <p><strong>Destino:</strong> ${oferta.itinerarioIda.listaSeguimientos.slice(-1)[0].destino}</p>
      <p><strong>Salida:</strong> ${oferta.itinerarioIda.listaSeguimientos[0].fechaSalida}</p>
      <p><strong>Regreso:</strong> ${oferta.itinerarioVuelta ? oferta.itinerarioVuelta.listaSeguimientos[0].fechaSalida : 'Sin vuelta'}</p>
      <p><strong>Precio:</strong> ${oferta.precio} €</p>
    `;

        contenedor.appendChild(vueloCard);
    });
}

document.getElementById("search-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const origen = document.getElementById("origen").value;
    const destino = document.getElementById("destino").value;
    const fechaIda = document.getElementById("fechaIda").value;
    const fechaVuelta = document.getElementById("fechaVuelta").value;

    let url = `http://localhost:8080/vuelos?origen=${encodeURIComponent(origen)}&destino=${encodeURIComponent(destino)}&fechaIda=${fechaIda}`;
    if (fechaVuelta) {
        url += `&fechaVuelta=${fechaVuelta}`;
    }

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Error al obtener vuelos");
            return response.json();
        })
        .then(data => {
            mostrarVuelos(data);
        })
        .catch(error => {
            console.error(error);
            alert("Error al cargar vuelos.");
        });
});

function mostrarVuelos(data) {
    const contenedor = document.getElementById("resultados-vuelos");
    contenedor.innerHTML = "";

    if (!data.listaOfertas || data.listaOfertas.length === 0) {
        contenedor.innerHTML = "<p>No se encontraron vuelos.</p>";
        return;
    }

    data.listaOfertas.forEach((oferta, index) => {
        const vueloDiv = document.createElement("div");
        vueloDiv.classList.add("vuelo-card");
        vueloDiv.style.border = "1px solid #ccc";
        vueloDiv.style.padding = "10px";
        vueloDiv.style.marginBottom = "10px";

        const origen = oferta.itinerarioIda.listaSeguimientos[0].origen;
        const destino = oferta.itinerarioIda.listaSeguimientos.slice(-1)[0].destino;
        const fechaSalida = oferta.itinerarioIda.listaSeguimientos[0].fechaSalida;
        const fechaRegreso = oferta.itinerarioVuelta
            ? oferta.itinerarioVuelta.listaSeguimientos[0].fechaSalida
            : "Sin vuelta";
        const precio = oferta.precio;

        vueloDiv.innerHTML = `
      <p><strong>Origen:</strong> ${origen}</p>
      <p><strong>Destino:</strong> ${destino}</p>
      <p><strong>Fecha de salida:</strong> ${fechaSalida}</p>
      <p><strong>Fecha de regreso:</strong> ${fechaRegreso}</p>
      <p><strong>Precio:</strong> ${precio} €</p>
      <button id="reservar-${index}">Reservar</button>
    `;

        contenedor.appendChild(vueloDiv);

        // Añadimos evento al botón reservar
        document.getElementById(`reservar-${index}`).addEventListener("click", () => {
            reservarVuelo({
                origen,
                destino,
                fechaSalida,
                fechaRegreso: fechaRegreso === "Sin vuelta" ? "" : fechaRegreso,
                precio
            });
        });
    });
}

function reservarVuelo(datosReserva) {

    const usuario = prompt("Por favor, introduce tu nombre de usuario para la reserva:");

    if (!usuario) {
        alert("Debe introducir un nombre de usuario para reservar.");
        return;
    }

    datosReserva.usuario = usuario;

    fetch("http://localhost:8080/reservas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(datosReserva)
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al reservar vuelo");
            return response.json();
        })
        .then(data => {
            alert(`Reserva realizada con éxito! ID reserva: ${data.id}`);
        })
        .catch(error => {
            console.error(error);
            alert("Error al realizar la reserva.");
        });
}


