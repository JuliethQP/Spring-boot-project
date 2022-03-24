package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuario() {
       String query="FROM Usuario";
      List<Usuario> resultado= entityManager.createQuery(query).getResultList();
      return resultado;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario =entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario verificarUsuario(Usuario usuario) {
        String query = "FROM Usuario WHERE nombre =:nombre AND apellido = :apellido";
       List <Usuario> lista=entityManager.createQuery(query)
               .setParameter("nombre",usuario.getNombre())
               .setParameter("apellido",usuario.getApellido())
               .getResultList();
       String contrasenaHashed=lista.get(0).getContrasena();
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean contrasenaCorrecta = argon2.verify(contrasenaHashed, usuario.getContrasena());

        if(contrasenaCorrecta){
           return lista.get(0);
       } else {
           return null;
       }
    }
}
