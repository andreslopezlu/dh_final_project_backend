
window.addEventListener("load", function(){


    
    let table = this.document.querySelector("table");
    let adminTurnos = document.querySelector(".admin-turnos");
    let agregar = document.querySelector(".agregar");

    table.style.display = "none";



    let urlGetAllTurnos = "/final/turnos/get/all";

    function renderizarTurnos(data){
    
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

    function consultarTurnos(){

    const settings = {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }

    fetch(urlGetAllTurnos, settings)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            renderizarTurnos(data);
            eliminarTurno();
        })
        .catch((error) => {
            console.error(error);
        })
    }
    
    function eliminarTurno(){
    
        let botonesEliminar = document.querySelectorAll("button.eliminar");
        
        botonesEliminar.forEach(boton => {
            boton.addEventListener("click", (e) => {
                let id = boton.getAttribute("id");
                console.log(id);
    
                let endpointEliminarPorId = `/final/turnos/delete/${id}`;

                const settings = {
                    method: 'DELETE',
                }

                console.log(endpointEliminarPorId);
            
                fetch(endpointEliminarPorId, settings)
                    .then((response) => {
                        return response.json();
                    })
                    .then((data) => {
                        console.log(data);
                        consultarTurnos();
                    })
                    .catch((error) => {
                        console.error(error);
                    })                
            })
        }
        )
    }

    adminTurnos.addEventListener("click", (e) => {
        table.style.display = "initial";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Turnos";

        agregar.innerText = "Agregar turno";

        consultarTurnos();
    })



})