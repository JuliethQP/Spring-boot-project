package com.cursojava.curso.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
@ToString @EqualsAndHashCode

public class Usuario {
    @Id
    @Getter  @Setter @Column(name="id")
    private long id;
    @Getter  @Setter @Column(name="nombre")
    private String nombre;
    @Getter  @Setter @Column(name="apellido")
    private String apellido;
    @Getter  @Setter @Column(name="contrasena")
    private String contrasena;
    @Getter  @Setter @Column(name="token")
    private String token;


}
