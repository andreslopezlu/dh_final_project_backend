window.addEventListener("load", function(){

// ----------------------------------------------------------------------------------------------------------------------- //
    
    let table = this.document.querySelector("table");
    let adminPacientes = document.querySelector(".admin-pacientes");
    let formularioModificar = document.querySelector("form.formulario-modificar");
    let formularioAgregar = document.querySelector("form.formulario-agregar");

    table.style.display = "none";
    formularioModificar.style.display = "none";
    formularioAgregar.style.display = "none";

// ----------------------------------------------------------------------------------------------------------------------- //

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

    function renderizarAgregar(){

        let agregarNuevo = document.querySelector("div.agregar");
        let buttonAgregarNuevo = document.querySelector("button.agregar-nuevo");

        if(buttonAgregarNuevo){
            buttonAgregarNuevo.remove();
        }

        buttonAgregarNuevo = document.createElement("button");
        buttonAgregarNuevo.innerText = "Agregar Paciente";
        buttonAgregarNuevo.classList.add("agregar-nuevo");
        agregarNuevo.appendChild(buttonAgregarNuevo);

    }

    function consultarPacientes(){

            let endpointGetAllPacientes = "/final/pacientes/get/all";

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
                renderizarAgregar();
                eliminarPaciente();
                consultarPaciente();
                agregarPaciente();
            })
            .catch((error) => {
                console.error(error);
            })
    }

// ----------------------------------------------------------------------------------------------------------------------- //

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

// ----------------------------------------------------------------------------------------------------------------------- //

    function renderizarPaciente(data){

        let formularioModificar = document.querySelector("form.formulario-modificar");

        let dataFormulario = document.querySelector("form.formulario-modificar div");

        if(dataFormulario){
            dataFormulario.remove();
        }

        formularioModificar.style.display = "initial";

        dataFormulario = document.createElement("div");
        dataFormulario.setAttribute("class", "data");

        dataFormulario.innerHTML = 
        `<section>
        <p>Datos básicos</p>
        <div>
            <label for="id">Id Paciente</label>
            <input type="text" name="id" id="id" autocomplete="off" readonly value=${data.id}>
        </div>
        <div>
            <label for="nombre">Nombre</label>
            <input type="text" name="nombre" id="nombre" autocomplete="off" onsubmit="required()" placeholder="Pepito" value=${data.nombre}>
        </div>
        <div>
            <label for="apellido">Apellido</label>
            <input type="text" name="apellido" id="apellido" autocomplete="off" onsubmit="required()" placeholder="Perez" value=${data.apellido}>
        </div>
        <div>
            <label for="dni">DNI</label>
            <input type="text" name="dni" id="dni" autocomplete="off" onsubmit="required()" placeholder="1234567890" value=${data.dni}>
        </div>
        <p>Domicilio</p>
        <div>
            <label for="id-domicilio">Id Domicilio</label>
            <input type="text" name="id-domicilio" id="id-domicilio" autocomplete="off" readonly value=${data.domicilio.id}>
        </div>
        <div>
            <label for="domicilio">Calle</label>
            <input type="text" name="calle" id="calle" autocomplete="off" onsubmit="required()" placeholder="Robles" value=${data.domicilio.calle}>
        </div>
        <div>
            <label for="domicilio">Numero</label>
            <input type="text" name="numero" id="numero" autocomplete="off" onsubmit="required()" placeholder="48" value=${data.domicilio.numero}>
        </div>
        <div>
            <label for="domicilio">Provincia</label>
            <input type="text" name="provincia" id="provincia" autocomplete="off" onsubmit="required()" placeholder="Cund" value=${data.domicilio.provincia}>
        </div>
        <div>
            <label for="domicilio">País</label>
            <input type="text" name="pais" id="pais" autocomplete="off" onsubmit="required()" placeholder="Col" value=${data.domicilio.pais}>
        </div>
        <div>
            <label for="domicilio">Código postal</label>
            <input type="text" name="codigoPostal" id="codigoPostal" autocomplete="off" onsubmit="required()" placeholder="110141" value=${data.domicilio.codigoPostal}>
        </div>
        <p>Otros datos</p>
        <div>
            <label for="fecha-alta">Fecha alta</label>
            <input type="text" name="fechaAlta" id="fechaAlta" autocomplete="off" onsubmit="required()" placeholder="2023-03-10"value=${data.fechaAlta}>
        </div>
        </section>
        <br>
        <section class="accept-modifications">
        <input class="accept-modifications" type="submit" value="Aceptar modificación">
        </section>`

        formularioModificar.appendChild(dataFormulario);

    }

    function consultarPaciente(){

        let botonesModificar = document.querySelectorAll("button.modificar");

        botonesModificar.forEach(boton => {
            boton.addEventListener("click", (e) => {

                clearForm();

                let formularioAgregar = document.querySelector("form.formulario-agregar");

                if(formularioAgregar){
                    formularioAgregar.style.display = "none";
                }                

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

                        let paciente = {
                            id: data.id == null ? "" : data.id,
                            nombre: data.nombre == null ? "" : data.nombre,
                            apellido: data.apellido == null ? "" : data.apellido,
                            dni: data.dni == null ? "" : data.dni,
                            domicilio: {
                                id: data.domicilio.id == null ? "" : data.domicilio.id,
                                calle: data.domicilio.calle == null ? "" : data.domicilio.calle,
                                numero: data.domicilio.numero == null ? "" : data.domicilio.numero,
                                provincia: data.domicilio.provincia == null ? "" : data.domicilio.provincia,
                                pais: data.domicilio.pais == null ? "" : data.domicilio.pais,
                                codigoPostal: data.domicilio.codigoPostal == null ? "" : data.domicilio.codigoPostal,
                            },
                            fechaAlta: data.fechaAlta == null ? "" : data.fechaAlta
                        }

                        renderizarPaciente(paciente);
                        aceptarModificacionPaciente();
                    })
                    .catch((error) => {
                        console.error(error);
                    })  
            })
        })
    }   

    function capturarModificacionPaciente(){

        let idPaciente = document.querySelector("input#id").value;
        let nombrePaciente = document.querySelector("input#nombre").value;
        let apellidoPaciente = document.querySelector("input#apellido").value;
        let dniPaciente = document.querySelector("input#dni").value;

        let idDomicilio = document.querySelector("input#id-domicilio").value;
        let calleDomicilio = document.querySelector("input#calle").value;
        let numeroDomicilio = document.querySelector("input#numero").value;
        let provinciaDomicilio = document.querySelector("input#provincia").value;
        let paisDomicilio = document.querySelector("input#pais").value;
        let codigoPostalDomicilio = document.querySelector("input#codigoPostal").value;

        let fechaAltaPaciente = document.querySelector("input#fechaAlta").value;

        let paciente = {
            id: idPaciente,
            nombre: nombrePaciente,
            apellido: apellidoPaciente,
            dni: dniPaciente,
            domicilio: {
                id: idDomicilio,
                calle: calleDomicilio,
                numero: numeroDomicilio,
                provincia: provinciaDomicilio,
                pais: paisDomicilio,
                codigoPostal: codigoPostalDomicilio,
            },
            fechaAlta: fechaAltaPaciente
        }

        return paciente;

    }

    function aceptarModificacionPaciente(){

        let aceptarModificacion = document.querySelector(".accept-modifications");

        aceptarModificacion.addEventListener("click", (e) => {

            e.preventDefault();
            
            let paciente = capturarModificacionPaciente();

            let endpointPutPaciente = "/final/pacientes/put";

            const settings = {
                method: 'PUT',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
                body: JSON.stringify(paciente)
            }

            fetch(endpointPutPaciente, settings)
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

// ----------------------------------------------------------------------------------------------------------------------- //

function renderizarFormularioNuevoPaciente(){

    let formularioAgregar = document.querySelector("form.formulario-agregar");

    let dataFormulario = document.querySelector("form.formulario-agregar div");

    if(dataFormulario){
        dataFormulario.remove();
    }

    formularioAgregar.style.display = "initial";

    dataFormulario = document.createElement("div");
    dataFormulario.setAttribute("class", "data");

    dataFormulario.innerHTML = 
    `<section>
    <p>Datos básicos</p>
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" autocomplete="off" onsubmit="required()" placeholder="Pepito">
    </div>
    <div>
        <label for="apellido">Apellido</label>
        <input type="text" name="apellido" id="apellido" autocomplete="off" onsubmit="required()" placeholder="Perez">
    </div>
    <div>
        <label for="dni">DNI</label>
        <input type="text" name="dni" id="dni" autocomplete="off" onsubmit="required()" placeholder="1234567890">
    </div>
    <p>Domicilio</p>
    <div>
        <label for="domicilio">Calle</label>
        <input type="text" name="calle" id="calle" autocomplete="off" onsubmit="required()" placeholder="Robles">
    </div>
    <div>
        <label for="domicilio">Numero</label>
        <input type="text" name="numero" id="numero" autocomplete="off" onsubmit="required()" placeholder="48">
    </div>
    <div>
        <label for="domicilio">Provincia</label>
        <input type="text" name="provincia" id="provincia" autocomplete="off" onsubmit="required()" placeholder="Cund">
    </div>
    <div>
        <label for="domicilio">País</label>
        <input type="text" name="pais" id="pais" autocomplete="off" onsubmit="required()" placeholder="Col">
    </div>
    <div>
        <label for="domicilio">Código postal</label>
        <input type="text" name="codigoPostal" id="codigoPostal" autocomplete="off" onsubmit="required()" placeholder="110141">
    </div>
    <p>Otros datos</p>
    <div>
        <label for="fecha-alta">Fecha alta</label>
        <input type="text" name="fechaAlta" id="fechaAlta" autocomplete="off" onsubmit="required()" placeholder="2023-03-10">
    </div>
    </section>
    <br>
    <section class="agregar-nuevo-paciente">
    <input class="button-agregar-nuevo-paciente" type="submit" value="Guardar paciente">
    </section>
    <br>`

    formularioAgregar.appendChild(dataFormulario);

}

function capturarNuevoPaciente(){

    let nombrePaciente = document.querySelector("input#nombre").value;
    let apellidoPaciente = document.querySelector("input#apellido").value;
    let dniPaciente = document.querySelector("input#dni").value;

    let calleDomicilio = document.querySelector("input#calle").value;
    let numeroDomicilio = document.querySelector("input#numero").value;
    let provinciaDomicilio = document.querySelector("input#provincia").value;
    let paisDomicilio = document.querySelector("input#pais").value;
    let codigoPostalDomicilio = document.querySelector("input#codigoPostal").value;

    let fechaAltaPaciente = document.querySelector("input#fechaAlta").value;

    let paciente = {
        nombre: nombrePaciente,
        apellido: apellidoPaciente,
        dni: dniPaciente,
        domicilio: {
            calle: calleDomicilio,
            numero: numeroDomicilio,
            provincia: provinciaDomicilio,
            pais: paisDomicilio,
            codigoPostal: codigoPostalDomicilio,
        },
        fechaAlta: fechaAltaPaciente
    }

    return paciente;

}

function agregarPaciente(){

    let nuevoPaciente = document.querySelector("button.agregar-nuevo");
    
    nuevoPaciente.addEventListener("click", (e) => {

        clearForm();

        let formularioModificar = document.querySelector("form.formulario-modificar");

            if(formularioModificar){
                formularioModificar.style.display = "none";
            }
        
        renderizarFormularioNuevoPaciente();
        aceptarGuardarPaciente();
    })

}

function aceptarGuardarPaciente(){

    let nuevoPaciente = document.querySelector("input.button-agregar-nuevo-paciente");

    nuevoPaciente.addEventListener("click", (e) => {

        e.preventDefault();

        let paciente = capturarNuevoPaciente();

        let endpointPutPaciente = "/final/pacientes/post";

        const settings = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(paciente)
        }

        fetch(endpointPutPaciente, settings)
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

// ----------------------------------------------------------------------------------------------------------------------- //


function clearForm(){
    
    let inputs = document.querySelectorAll("input");

    inputs.forEach(input => {
        input.value = "";
    })

}

// ----------------------------------------------------------------------------------------------------------------------- //

    adminPacientes.addEventListener("click", (e) => {
        table.style.display = "initial";

        let adminTitle = document.querySelector(".admin");
        adminTitle.innerText = "Pacientes";

        // agregar.innerText = "Agregar paciente";

        // agregar.style.display = "initial";

        consultarPacientes();
    })

// ----------------------------------------------------------------------------------------------------------------------- //

})