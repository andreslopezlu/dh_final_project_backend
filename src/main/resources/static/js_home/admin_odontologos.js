
window.addEventListener("load", function(){


    
    let table = this.document.querySelector("table");
    let adminOdontologos = document.querySelector(".admin-odontologos");
    let agregar = document.querySelector(".agregar");

    table.style.display = "none";



    let urlGetAllOdontologos = "/final/odontologos/get/all"

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
            let matricula = dato.matricula;
    
            let html = 
            `<tr class="data">
                <td class="id">${id}</td>
                <td class="attributes">
                    <p>Nombre: ${nombre}</p>
                    <p>Apellido: ${apellido}</p>
                    <p>Matricula: ${matricula}</p>
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

    function consultarOdontologos(){

    const settings = {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }

    fetch(urlGetAllOdontologos, settings)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            renderizarOdontologos(data);
            eliminarOdontologo();
        })
        .catch((error) => {
            console.error(error);
        })
    }
    
    function eliminarOdontologo(){
    
        let botonesEliminar = document.querySelectorAll("button.eliminar");
        
        botonesEliminar.forEach(boton => {
            boton.addEventListener("click", (e) => {
                let id = boton.getAttribute("id");
                console.log(id);
    
                let endpointEliminarPorId = `/final/odontologos/delete/${id}`;

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
                        consultarOdontologos();
                    })
                    .catch((error) => {
                        console.error(error);
                    })                
            })
        }
        )
    }

    adminOdontologos.addEventListener("click", (e) => {
        table.style.display = "initial";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Odontologos";

        agregar.innerText = "Agregar odontologo";

        consultarOdontologos();
    })



})