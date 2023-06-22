
window.addEventListener("load", function(){


    
    let table = this.document.querySelector("table");
    let adminPacientes = document.querySelector(".admin-pacientes");
    let agregar = document.querySelector(".agregar");
    let formularioModificar = document.querySelector("form");

    table.style.display = "none";
    formularioModificar.style.display = "none";



    let endpointGetAllPacientes = "/final/pacientes/get/all";

    function renderizarPacientes(data){
    
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

    function consultarPacientes(){

        const settings = {
            method: 'GET',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        }
    
        fetch(endpointGetAllPacientes, settings)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                console.log(data);
                renderizarPacientes(data);
                eliminarPaciente();
                modificarPaciente();
            })
            .catch((error) => {
                console.error(error);
            })
    }

    function eliminarPaciente(){
    
        let botonesEliminar = document.querySelectorAll("button.eliminar");
        
        botonesEliminar.forEach(boton => {
            boton.addEventListener("click", (e) => {
                let id = boton.getAttribute("id");
    
                let endpointEliminarPorId = `/final/pacientes/delete/${id}`;

                const settings = {
                    method: 'DELETE',
                }
            
                fetch(endpointEliminarPorId, settings)
                    .then((response) => {
                        return response.json();
                    })
                    .then((data) => {
                        console.log(data);
                        consultarPacientes();
                    })
                    .catch((error) => {
                        console.error(error);
                    })                
            })
        }
        )
    }






    function renderizarPaciente(data){

        let formularioModificar = document.querySelector("form");

        let dataFormulario = document.querySelector("form div");

        if(dataFormulario){
            dataFormulario.remove();
        }

        formularioModificar.style.display = "initial";

        dataFormulario = document.createElement("div");
        dataFormulario.setAttribute("class", "data");

        dataFormulario.innerHTML = 
        `<section>
        <p>Datos basicos</p>
        <div>
            <label for="id">Id Paciente</label>
            <input type="text" name="id" id="id" autocomplete="off" readonly value=${data.id}>
        </div>
        <div>
            <label for="nombre">Nombre</label>
            <input type="text" name="nombre" id="nombre" autocomplete="off" value=${data.nombre}>
        </div>
        <div>
            <label for="apellido">Apellido</label>
            <input type="text" name="apellido" id="apellido" autocomplete="off" value=${data.apellido}>
        </div>
        <div>
            <label for="dni">DNI</label>
            <input type="text" name="dni" id="dni" autocomplete="off" value=${data.dni}>
        </div>
        <p>Domicilio</p>
        <div>
            <label for="domicilio">Calle</label>
            <input type="text" name="domicilio" id="domicilio" autocomplete="off" value=${data.domicilio.calle}>
        </div>
        <div>
            <label for="domicilio">Numero</label>
            <input type="text" name="domicilio" id="domicilio" autocomplete="off" value=${data.domicilio.numero}>
        </div>
        <div>
            <label for="domicilio">Provincia</label>
            <input type="text" name="domicilio" id="domicilio" autocomplete="off" value=${data.domicilio.provincia}>
        </div>
        <div>
            <label for="domicilio">País</label>
            <input type="text" name="domicilio" id="domicilio" autocomplete="off" value=${data.domicilio.pais}>
        </div>
        <div>
            <label for="domicilio">Codigo postal</label>
            <input type="text" name="domicilio" id="domicilio" autocomplete="off" value=${data.domicilio.codigoPostal}>
        </div>
        <p>Otros datos</p>
        <div>
            <label for="fecha-alta">Fecha alta</label>
            <input type="text" name="fecha-alta" id="fecha-alta" autocomplete="off" value=${data.fechaAlta}>
        </div>
        </section>
        <br>
        <section class="submission">
        <input class="login" type="submit" name="submit" id="submit" value="Aceptar modificación">
        </section>`

        formularioModificar.appendChild(dataFormulario);

    }

    function modificarPaciente(){

        let botonesModificar = document.querySelectorAll("button.modificar");

        botonesModificar.forEach(boton => {
            boton.addEventListener("click", (e) => {
                let id = boton.getAttribute("id");

                let endpointGetPaciente = `/final/pacientes/get/${id}`;

                const settings = {
                    method: 'GET',
                }

                fetch(endpointGetPaciente, settings)
                    .then((response) => {
                        return response.json();
                    })
                    .then((data) => {
                        console.log(data);
                        renderizarPaciente(data);
                    })
                    .catch((error) => {
                        console.error(error);
                    })  
            })
        })
    }

    adminPacientes.addEventListener("click", (e) => {
        table.style.display = "initial";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Pacientes";

        agregar.innerText = "Agregar paciente";

        consultarPacientes();
    })



})