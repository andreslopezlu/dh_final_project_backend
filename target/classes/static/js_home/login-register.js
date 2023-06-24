
function removerRoles(){
    let sectionRoles = document.querySelector("section.rol");
    if(sectionRoles){
        sectionRoles.remove();
    }
    let inputSubmit = document.querySelector("input#submit");
    inputSubmit.classList.remove("register");
    inputSubmit.classList.add("login");
}


function renderizarRoles(){

    let divData = document.querySelector(".data");
    let sectionRoles = document.querySelector("section.rol");

    if(sectionRoles){
        sectionRoles.remove();
    }

    sectionRoles = document.createElement("section");
    sectionRoles.classList.add("rol")

    sectionRoles.innerHTML = 
    `<div class="roles">
        <p>Seleccione un rol</p>
        <section class="roles">
            <input type="radio" name="rol" id="admin" value="ROLE_ADMIN" required>
            <label for="admin">Administrador</label>
        </section>
        <section class="roles">
            <input type="radio" name="rol" id="user" value="ROLE_USER" required>
            <label for="user">Usuario</label>
        </section>
    </div>`

    divData.appendChild(sectionRoles);

    let inputSubmit = document.querySelector("input#submit");
    inputSubmit.classList.remove("login");
    inputSubmit.classList.add("register");

}


function capturarDatos(){

    let data = {
        usuario: "",
        password: "",
    }

    let inputUsuario = document.querySelector("#usuario");
    let inputPassword = document.querySelector("#password");
    let inputRoles = document.querySelectorAll("[name=rol]");

    data.usuario = inputUsuario.value;
    data.password = inputPassword.value;

    let sectionRoles = document.querySelector("section.rol");

    if (sectionRoles){
        data['rol'] = "";
        inputRoles.forEach(rol => {
            if(rol.checked){
                data['rol'] = rol.value;
            }
        })
    }

    return data;
}


function post(endpoint) {

    const data = capturarDatos();

    const url = endpoint;

    const settings = {
        method: 'POST',
        body: JSON.stringify(data),
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
            return data;
        })
        .catch((error) => {
            console.error(error);
        })
}

window.addEventListener("load", function() {

    let buttonLogin = document.querySelector("button.login");
    let buttonRegister = document.querySelector("button.register");
    let formulario = document.querySelector("form");


    buttonLogin.addEventListener("click", (e) => {
        removerRoles();
    })

    buttonRegister.addEventListener("click", (e) => {
        renderizarRoles();
    })

    formulario.addEventListener("submit", (e) => {
        let inputSubmit = document.querySelector("input#submit");
        let endpointGuardarUsuario = "/final/usuarios/post"
        if(inputSubmit.classList.contains("register")){
            e.preventDefault();
            post(endpointGuardarUsuario);
        }
    })

})







