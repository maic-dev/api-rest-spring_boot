package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.IUserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

//Estos servicios son clases que contienen la lógica de negocio
//Se encargan de interactuar con los IRepositories

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public ArrayList<Usuario> getUsuarios() {
        return (ArrayList<Usuario>) userRepository.findAll();
    }

    public Usuario saveUsuario(Usuario user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Hashear la contraseña utilizando argon2-jvm
        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(2, 65536, 1, user.getPassword());
        user.setPassword(hash);

        return userRepository.save(user);
    }

    public Usuario getUsuarioById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Long id, Usuario user) {
        Usuario existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUsuario(Long id) {
        userRepository.deleteById(id);
    }

    public boolean signIn(Usuario user) {
        Usuario findUser = userRepository.findByUsername(user.getUsername());

        if (findUser == null) {
            return false;
        }

        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(findUser.getPassword(), user.getPassword());
    }
    
}
