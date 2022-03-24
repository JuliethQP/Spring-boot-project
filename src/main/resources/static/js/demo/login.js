// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function  iniciarSesion(){
    const nombre=document.getElementById("nombre").value;
    const apellido=document.getElementById("apellido").value;
     const contrasena=document.getElementById("contrasena").value;
     let datos={nombre:nombre,apellido:apellido,contrasena:contrasena};
     try{
         const request = await fetch('api/iniciarSesion', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body:JSON.stringify(datos)
          });
          const res=await request.json();
          console.log(res);
          if (res) {
             window.location.href="usuarios.html"
          }else {
            alert("las credenciales son incorrectas")
          }
     }
     catch(err){
         console.log(err)
     }
};


