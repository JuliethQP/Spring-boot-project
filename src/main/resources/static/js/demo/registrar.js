// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function  registrarUsuario(){
    const nombre=document.getElementById("nombre").value;
    const apellido=document.getElementById("apellido").value;
    const contrasena=document.getElementById("contrasena").value;
     let datos={nombre:nombre,apellido:apellido,contrasena:contrasena};
     const request = await fetch('api/registrarUsuario', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body:JSON.stringify(datos)
     });
     const usuarios = await request.json();
};


