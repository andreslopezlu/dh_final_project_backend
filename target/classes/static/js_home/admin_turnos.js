window.addEventListener("load", function(){

    // ----------------------------------------------------------------------------------------------------------------------- //
        
        let table = this.document.querySelector("table");
        let adminturnos = document.querySelector(".admin-turnos");
        let formularioModificar = document.querySelector("form.formulario-modificar");
        let formularioAgregar = document.querySelector("form.formulario-agregar");
    
        table.style.display = "none";
        formularioModificar.style.display = "none";
        formularioAgregar.style.display = "none";
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        function renderizarturnos(data){
        
            let bodyTable = document.querySelector(".body-table");
            let dataTable = document.querySelectorAll(".data");
        
            if(dataTable.length > 0){
                dataTable.forEach(item => {
                    item.remove()
                })
            }
        
            for (dato of data){
        
                let id = dato.id;
                let turnoId = dato.id;
                let pacienteId = dato.paciente.id;
                let OdontologoId = dato.odontologo.id;
                let fecha = dato.fecha;
                let hora = dato.hora;
        
                let html = 
                `<tr class="data">
                    <td class="id">${id}</td>
                    <td class="attributes">
                        <p>Id turno: ${turnoId}</p>
                        <p>Id Paciente: ${pacienteId}</p>
                        <p>Id Odontologo: ${OdontologoId}</p>
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
    
        function renderizarAgregar(){
    
            let agregarNuevo = document.querySelector("div.agregar");
            let buttonAgregarNuevo = document.querySelector("button.agregar-nuevo");
    
            if(buttonAgregarNuevo){
                buttonAgregarNuevo.remove();
            }
    
            buttonAgregarNuevo = document.createElement("button");
            buttonAgregarNuevo.innerText = "Agregar turno";
            buttonAgregarNuevo.classList.add("agregar-nuevo");
            agregarNuevo.appendChild(buttonAgregarNuevo);
    
        }
    
        function consultarturnos(){
    
                let endpointGetAllturnos = "/final/turnos/get/all";
    
            const settings = {
                method: 'GET',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
            }
        
            fetch(endpointGetAllturnos, settings)
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    console.log(data);
                    renderizarturnos(data);
                    renderizarAgregar();
                    eliminarturno();
                    consultarturno();
                    agregarturno();
                })
                .catch((error) => {
                    console.error(error);
                })
        }
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        function eliminarturno(){
        
            let botonesEliminar = document.querySelectorAll("button.eliminar");
            
            botonesEliminar.forEach(boton => {
                boton.addEventListener("click", (e) => {
                    let id = boton.getAttribute("id");
        
                    let endpointEliminarPorId = `/final/turnos/delete/${id}`;
    
                    const settings = {
                        method: 'DELETE',
                    }
                
                    fetch(endpointEliminarPorId, settings)
                        .then((response) => {
                            return response.json();
                        })
                        .then((data) => {
                            console.log(data);
                            consultarturnos();
                        })
                        .catch((error) => {
                            console.error(error);
                        })                
                })
            }
            )
        }
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
        function renderizarturno(data){
    
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
                <label for="id">Id turno</label>
                <input type="text" name="id" id="id" autocomplete="off" readonly value=${data.id}>
            </div>
            <div>
                <label for="pacienteid">Id Paciente</label>
                <input type="text" name="pacienteid" id="pacienteid" autocomplete="off" value=${data.paciente.id}>
            </div>
            <div>
                <label for="odontologoid">Id Odontologo</label>
                <input type="text" name="odontologoid" id="odontologoid" autocomplete="off" value=${data.odontologo.id}>
            </div>
            <div>
                <label for="fecha">Fecha</label>
                <input type="text" name="fecha" id="fecha" autocomplete="off" onsubmit="required()" placeholder="Formato=2023-03-10" value=${data.fecha}>
            </div>
            <div>
            <label for="hora">Hora</label>
            <input type="text" name="hora" id="hora" autocomplete="off" onsubmit="required()" placeholder="Formato=00:00:00" value=${data.hora}>
            </div>
            </section>
            <br>
            <section class="accept-modifications">
            <input class="accept-modifications" type="submit" value="Aceptar modificación">
            </section>`
    
            formularioModificar.appendChild(dataFormulario);
    
        }
    
        function consultarturno(){
    
            let botonesModificar = document.querySelectorAll("button.modificar");
    
            botonesModificar.forEach(boton => {
                boton.addEventListener("click", (e) => {
    
                    clearForm();
    
                    let formularioAgregar = document.querySelector("form.formulario-agregar");
    
                    if(formularioAgregar){
                        formularioAgregar.style.display = "none";
                    }                
    
                    let id = boton.getAttribute("id");
    
                    let endpointGetturno = `/final/turnos/get/${id}`;
    
                    const settings = {
                        method: 'GET',
                    }
    
                    fetch(endpointGetturno, settings)
                        .then((response) => {
                            return response.json();
                        })
                        .then((data) => {
                            console.log(data);
    
                            let turno = {
                                id: data.id == null ? "" : data.id,
                                paciente: data.paciente == null ? "" : data.paciente,
                                odontologo: data.odontologo == null ? "" : data.odontologo,
                                fecha: data.fecha == null ? "" : data.fecha,
                                hora: data.hora == null ? "" : data.hora,
                            }

                            console.log(turno)
                            console.log("aqui 1")
    
                            renderizarturno(turno);
                            aceptarModificacionturno();
                        })
                        .catch((error) => {
                            console.error(error);
                        })  
                })
            })
        }   
    
        function capturarModificacionturno(){
    
            let idturno = parseInt(document.querySelector("input#id").value);
            let idpaciente = parseInt(document.querySelector("input#pacienteid").value);
            let idodontologo = parseInt(document.querySelector("input#odontologoid").value);
            let fecha = document.querySelector("input#fecha").value;
            let hora = document.querySelector("input#hora").value;
    
            let turno = {
                id: idturno,
                paciente: {id: idpaciente},
                odontologo: {id : idodontologo},
                fecha: fecha,
                hora: hora,
            }
    
            return turno;
    
        }
    
        function aceptarModificacionturno(){
    
            let aceptarModificacion = document.querySelector(".accept-modifications");
    
            aceptarModificacion.addEventListener("click", (e) => {
    
                e.preventDefault();
                
                let turno = capturarModificacionturno();

                console.log(turno)
                console.log("aqui 2")
    
                let endpointPutturno = "/final/turnos/put";
    
                const settings = {
                    method: 'PUT',
                    headers: {
                        'Content-type': 'application/json; charset=UTF-8',
                    },
                    body: JSON.stringify(turno)
                }
    
                fetch(endpointPutturno, settings)
                        .then((response) => {
                            return response.json();
                        })
                        .then((data) => {
                            console.log(data);
                            consultarturnos();
                        })
                        .catch((error) => {
                            console.error(error);
                        })  
            })
        }
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
    function renderizarFormularioNuevoTurno(){
    
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
            <label for="pacienteid">Id Paciente</label>
            <input type="text" name="pacienteid" id="pacienteid" autocomplete="off" placeholder="1">
        </div>
        <div>
            <label for="odontologoid">Id Odontologo</label>
            <input type="text" name="odontologoid" id="odontologoid" autocomplete="off" placeholder="1">
        </div>
        <div>
            <label for="fecha">Fecha</label>
            <input type="text" name="fecha" id="fecha" autocomplete="off" onsubmit="required()" placeholder="Formato=2023-03-10">
        </div>
        <div>
        <label for="hora">Hora</label>
        <input type="text" name="hora" id="hora" autocomplete="off" onsubmit="required()" placeholder="Formato=00:00:00">
        </div>
        </section>
        <br>
        <section class="agregar-nuevo-turno">
        <input class="button-agregar-nuevo-turno" type="submit" value="Guardar turno">
        </section>
        <br>`
    
        formularioAgregar.appendChild(dataFormulario);
    
    }
    
    function capturarNuevoturno(){
    
        let pacienteid = parseInt(document.querySelector("input#pacienteid").value);
        let odontologoid = parseInt(document.querySelector("input#odontologoid").value);
        let fecha = document.querySelector("input#fecha").value;
        let hora = document.querySelector("input#hora").value;
    
        let turno = {
            paciente: {id: pacienteid},
            odontologo: {id: odontologoid},
            fecha: fecha,
            hora: hora,
        }

        console.log(typeof turno.paciente.id)
        return turno;
    
    }
    
    function agregarturno(){
    
        let nuevoturno = document.querySelector("button.agregar-nuevo");
        
        nuevoturno.addEventListener("click", (e) => {
    
            clearForm();
    
            let formularioModificar = document.querySelector("form.formulario-modificar");
    
                if(formularioModificar){
                    formularioModificar.style.display = "none";
                }
            
            renderizarFormularioNuevoTurno();
            aceptarGuardarturno();
            console.log("ok ok ok")
        })
    
    }
    
    function aceptarGuardarturno(){
    
        let nuevoturno = document.querySelector("input.button-agregar-nuevo-turno");
    
        nuevoturno.addEventListener("click", (e) => {
    
            e.preventDefault();
    
            let turno = capturarNuevoturno();
    
            let endpointPostTurno = "/final/turnos/post";
    
            const settings = {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
                body: JSON.stringify(turno)
            }
    
            fetch(endpointPostTurno, settings)
                    .then((response) => {
                        console.log("ok")
                        return response.json();
                    })
                    .then((data) => {
                        console.log("ok")
                        console.log(data);
                        consultarturnos();
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
    
        adminturnos.addEventListener("click", (e) => {
            table.style.display = "initial";
    
            let adminTitle = document.querySelector(".admin");
            adminTitle.innerText = "Turnos";
    
            // agregar.innerText = "Agregar paciente";
    
            // agregar.style.display = "initial";
    
            consultarturnos();
        })
    
    // ----------------------------------------------------------------------------------------------------------------------- //
    
    })