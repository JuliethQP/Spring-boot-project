package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController implements UsuarioDao {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucar");
        usuario.setApellido("Moy");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuario(){
        List<Usuario> usuarios=new ArrayList<>();
        return usuarioDao.getUsuario();
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);

    }


    @RequestMapping(value = "usuario2")
    public Usuario buscar(){
        Usuario usuario= new Usuario();
        usuario.setNombre("Lucar");
        usuario.setApellido("Moy");
        return usuario;
    }

    @RequestMapping(value = "api/registrarUsuario",method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1,1024,1,usuario.getContrasena());
        usuario.setContrasena(hash);
        usuarioDao.registrarUsuario(usuario);
    }



    @RequestMapping(value = "api/iniciarSesion",method = RequestMethod.POST)
    public Usuario verificarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioLogueado=usuarioDao.verificarUsuario(usuario);
        if(usuarioLogueado !=null) {
            String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getNombre());
            usuarioLogueado.setToken(token);
            return usuarioLogueado;
        } else{
            return null;
        }
    }
}
