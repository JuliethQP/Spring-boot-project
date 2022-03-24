package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
   List<Usuario> getUsuario();
   void eliminar(Long id);

   void registrarUsuario(Usuario usuario);

   Usuario verificarUsuario(Usuario usuario);
}
