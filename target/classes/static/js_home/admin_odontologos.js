window.addEventListener("load", function(){

    // ----------------------------------------------------------------------------------------------------------------------- //
        
        let table = this.document.querySelector("table");
        let adminOdontologos = document.querySelector(".admin-odontologos");
        let formularioModificar = document.querySelector("form.formulario-modificar");
        let formularioAgregar = document.querySelector("form.formulario-agregar");
    
        table.style.display = "none";
        formularioModificar.style.display = "none";
        formularioAgregar.style.display = "none";
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
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
    
        function renderizarAgregar(){
    
            let agregarNuevo = document.querySelector("div.agregar");
            let buttonAgregarNuevo = document.querySelector("button.agregar-nuevo");
    
            if(buttonAgregarNuevo){
                buttonAgregarNuevo.remove();
            }
    
            buttonAgregarNuevo = document.createElement("button");
            buttonAgregarNuevo.innerText = "Agregar Odontologo";
            buttonAgregarNuevo.classList.add("agregar-nuevo");
            agregarNuevo.appendChild(buttonAgregarNuevo);
    
        }
    
        function consultarOdontologos(){
    
                let endpointGetAllOdontologos = "/final/odontologos/get/all";
    
            const settings = {
                method: 'GET',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
            }
        
            fetch(endpointGetAllOdontologos, settings)
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    console.log(data);
                    renderizarOdontologos(data);
                    renderizarAgregar();
                    eliminarOdontologo();
                    consultarOdontologo();
                    agregarOdontologo();
                })
                .catch((error) => {
                    console.error(error);
                })
        }
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        function eliminarOdontologo(){
        
            let botonesEliminar = document.querySelectorAll("button.eliminar");
            
            botonesEliminar.forEach(boton => {
                boton.addEventListener("click", (e) => {
                    let id = boton.getAttribute("id");
        
                    let endpointEliminarPorId = `/final/odontologos/delete/${id}`;
    
                    const settings = {
                        method: 'DELETE',
                    }
                
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
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        function renderizarOdontologo(data){
    
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
                <label for="id">Id Odontologo</label>
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
                <label for="matricula">Matricula</label>
                <input type="text" name="matricula" id="matricula" autocomplete="off" onsubmit="required()" placeholder="1234567890" value=${data.matricula}>
            </div>
            </section>
            <br>
            <section class="accept-modifications">
            <input class="accept-modifications" type="submit" value="Aceptar modificación">
            </section>`
    
            formularioModificar.appendChild(dataFormulario);
    
        }
    
        function consultarOdontologo(){
    
            let botonesModificar = document.querySelectorAll("button.modificar");
    
            botonesModificar.forEach(boton => {
                boton.addEventListener("click", (e) => {
    
                    clearForm();
    
                    let formularioAgregar = document.querySelector("form.formulario-agregar");
    
                    if(formularioAgregar){
                        formularioAgregar.style.display = "none";
                    }                
    
                    let id = boton.getAttribute("id");
    
                    let endpointGetOdontologo = `/final/odontologos/get/${id}`;
    
                    const settings = {
                        method: 'GET',
                    }
    
                    fetch(endpointGetOdontologo, settings)
                        .then((response) => {
                            return response.json();
                        })
                        .then((data) => {
                            console.log(data);
    
                            let odontologo = {
                                id: data.id == null ? "" : data.id,
                                nombre: data.nombre == null ? "" : data.nombre,
                                apellido: data.apellido == null ? "" : data.apellido,
                                matricula: data.matricula == null ? "" : data.matricula,
                            }
    
                            renderizarOdontologo(odontologo);
                            aceptarModificacionOdontologo();
                        })
                        .catch((error) => {
                            console.error(error);
                        })  
                })
            })
        }   
    
        function capturarModificacionOdontologo(){
    
            let idOdontologo = document.querySelector("input#id").value;
            let nombreOdontologo = document.querySelector("input#nombre").value;
            let apellidoOdontologo = document.querySelector("input#apellido").value;
            let matriculaOdontologo = document.querySelector("input#matricula").value;
    
            let odontologo = {
                id: idOdontologo,
                nombre: nombreOdontologo,
                apellido: apellidoOdontologo,
                matricula: matriculaOdontologo,
            }
    
            return odontologo;
    
        }
    
        function aceptarModificacionOdontologo(){
    
            let aceptarModificacion = document.querySelector(".accept-modifications");
    
            aceptarModificacion.addEventListener("click", (e) => {
    
                e.preventDefault();
                
                let odontologo = capturarModificacionOdontologo();
    
                let endpointPutOdontologo = "/final/odontologos/put";
    
                const settings = {
                    method: 'PUT',
                    headers: {
                        'Content-type': 'application/json; charset=UTF-8',
                    },
                    body: JSON.stringify(odontologo)
                }
    
                fetch(endpointPutOdontologo, settings)
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
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
    function renderizarFormularioNuevoOdontologo(){
    
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
            <label for="matricula">Matricula</label>
            <input type="text" name="matricula" id="matricula" autocomplete="off" onsubmit="required()" placeholder="1234567890">
        </div>
        </section>
        <br>
        <section class="agregar-nuevo-odontologo">
        <input class="button-agregar-nuevo-odontologo" type="submit" value="Guardar odontologo">
        </section>
        <br>`
    
        formularioAgregar.appendChild(dataFormulario);
    
    }
    
    function capturarNuevoOdontologo(){
    
        let nombreOdontologo = document.querySelector("input#nombre").value;
        let apellidoOdontologo = document.querySelector("input#apellido").value;
        let matriculaOdontologo = document.querySelector("input#matricula").value;
    
        let odontologo = {
            nombre: nombreOdontologo,
            apellido: apellidoOdontologo,
            matricula: matriculaOdontologo,
        }
        return odontologo;
    
    }
    
    function agregarOdontologo(){
    
        let nuevoOdontologo = document.querySelector("button.agregar-nuevo");
        
        nuevoOdontologo.addEventListener("click", (e) => {
    
            clearForm();
    
            let formularioModificar = document.querySelector("form.formulario-modificar");
    
                if(formularioModificar){
                    formularioModificar.style.display = "none";
                }
            
            renderizarFormularioNuevoOdontologo();
            aceptarGuardarOdontologo();
        })
    
    }
    
    function aceptarGuardarOdontologo(){
    
        let nuevoOdontologo = document.querySelector("input.button-agregar-nuevo-odontologo");
    
        nuevoOdontologo.addEventListener("click", (e) => {
    
            e.preventDefault();
    
            let odontologo = capturarNuevoOdontologo();
    
            let endpointPutOdontologo = "/final/odontologos/post";
    
            const settings = {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
                body: JSON.stringify(odontologo)
            }
    
            fetch(endpointPutOdontologo, settings)
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
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
    
    function clearForm(){
        
        let inputs = document.querySelectorAll("input");
    
        inputs.forEach(input => {
            input.value = "";
        })
    
    }
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        adminOdontologos.addEventListener("click", (e) => {
            table.style.display = "initial";
    
            let adminTitle = document.querySelector(".admin");
            adminTitle.innerText = "Odontologos";
    
            // agregar.innerText = "Agregar paciente";
    
            // agregar.style.display = "initial";
    
            consultarOdontologos();
        })
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
    })