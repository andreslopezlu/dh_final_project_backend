function renderizarOdontologos(data){
    
    let bodyTable = document.querySelector(".body-table");
    let dataTable = document.querySelectorAll(".data");

    if(dataTable.length > 0){
        dataTable.forEach(item => {
            item.remove()
        })
    }

    for (dato of data){

        let id = dato.id;
        let nombre = dato.nombre;
        let apellido = dato.apellido;

        let html = 
        `<tr class="data id${id}">
            <td class="id">${id}</td>
            <td class="attributes">
                <p>Nombre: ${nombre}</p>
                <p>Apellido: ${apellido}</p>
            </td>
            <td>
                <button class="modificar" id="${id}">Modificar</button>
            </td>
            <td>
                <button class="eliminar" id="${id}">Eliminar</button>
            </td>
        </tr>`
        bodyTable.innerHTML += html;
    }

}


function capturarBotonesEliminar(){
    let botonesEliminar = document.querySelectorAll(`button.eliminar`);
    return botonesEliminar;
}


function eliminarOdontologo(){

    let botones = capturarBotonesEliminar();
    
    for(boton of botones){
        boton.addEventListener("click", (e) => {
            let adminTitle = document.querySelector(".admin");
            let tabla = adminTitle.getAttribute("id");
            console.log(tabla);
            let id = boton.getAttribute("id");
            console.log(id);

            let endpointEliminarPorId = `/final/${tabla}/delete/${id}`;
            deleteById(endpointEliminarPorId);

            let dataRemoved = document.querySelector("tr.data.id"+id);
            dataRemoved.remove();

            capturarBotonesEliminar();
            eliminarOdontologo();
        })
    }
}



function renderizarTablaPacientes(data){
    
    let bodyTable = document.querySelector(".body-table");
    let dataTable = document.querySelectorAll(".data");

    if(dataTable.length > 0){
        dataTable.forEach(item => {
            item.remove()
        })
    }

    for (dato of data){

        let id = dato.id;
        let nombre = dato.nombre;
        let apellido = dato.apellido;
        let dni = dato.dni;

        let html = 
        `<tr class="data">
            <td class="id">${id}</td>
            <td class="attributes">
                <p>Nombre: ${nombre}</p>
                <p>Apellido: ${apellido}</p>
                <p>DNI: ${dni}</p>
            </td>
            <td>
                <button class="modificar" id="${id}">Modificar</button>
            </td>
            <td>
                <button class="eliminar" id="${id}">Eliminar</button>
            </td>
        </tr>`
        bodyTable.innerHTML += html;
    }

}


function renderizarTablaTurnos(data){
    
    let bodyTable = document.querySelector(".body-table");
    let dataTable = document.querySelectorAll(".data");

    if(dataTable.length > 0){
        dataTable.forEach(item => {
            item.remove()
        })
    }

    for (dato of data){

        let id = dato.id;
        let pacienteId = dato.paciente.id;
        let odontologoId = dato.odontologo.id;
        let fecha = dato.fecha;
        let hora = dato.hora;

        let html = 
        `<tr class="data">
            <td class="id">${id}</td>
            <td class="attributes">
                <p>Id Paciente: ${pacienteId}</p>
                <p>Id Odontologo: ${odontologoId}</p>
                <p>Fecha: ${fecha}</p>
                <p>Hora: ${hora}</p>
            </td>
            <td>
                <button class="modificar" id="${id}">Modificar</button>
            </td>
            <td>
                <button class="eliminar" id="${id}">Eliminar</button>
            </td>
        </tr>`
        bodyTable.innerHTML += html;
    }
}


function getAll(endpoint, renderizar) {

    const url = endpoint;

    const settings = {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }

    fetch(url, settings)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            renderizar(data);
        })
        .catch((error) => {
            console.error(error);
        })
}


function deleteById(endpoint) {

    const url = endpoint;

    const settings = {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }

    fetch(url, settings)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
        })
        .catch((error) => {
            console.error(error);
        })
}


window.addEventListener("load", function(){

    let table = this.document.querySelector("table");
    let adminPacientes = document.querySelector(".admin-pacientes");
    let adminOdontologos = document.querySelector(".admin-odontologos");
    let adminTurnos = document.querySelector(".admin-turnos");
    let agregar = document.querySelector(".agregar");

    table.style.display = "none";

    adminPacientes.addEventListener("click", (e) => {
        table.style.display = "initial";

        agregar.innerText = "Agregar paciente";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Pacientes";
        adminTitle.setAttribute("id", "pacientes");

        let endpointMostrarTodosPacientes = "/final/pacientes/get/all";
        getAll(endpointMostrarTodosPacientes, renderizarTablaPacientes);
    })

    adminOdontologos.addEventListener("click", (e) => {
        table.style.display = "initial";

        agregar.innerText = "Agregar odontologo";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Odontologos";
        adminTitle.setAttribute("id", "odontologos");

        let endpointMostrarTodosOdontologos = "/final/odontologos/get/all";
        getAll(endpointMostrarTodosOdontologos, renderizarOdontologos);

    })
    
    adminTurnos.addEventListener("click", (e) => {
        table.style.display = "initial";

        agregar.innerText = "Agregar turno";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Turnos";
        adminTitle.setAttribute("id", "turnos");

        let endpointMostrarTodosTurnos = "/final/turnos/get/all";
        getAll(endpointMostrarTodosTurnos, renderizarTablaTurnos);
    })

})