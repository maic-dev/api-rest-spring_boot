package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.Rol;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.IRolRepository;
import com.example.demo.repositories.IUserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

//Estos servicios son clases que contienen la lógica de negocio
//Se encargan de interactuar con los IRepositories

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    public ArrayList<Usuario> getUsuarios() {
        return (ArrayList<Usuario>) userRepository.findAll();
    }

    public Usuario saveUsuario(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.username) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Obtener el rol desde la base de datos
        Rol rol = rolRepository.findById(userDTO.rol_id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + userDTO.rol_id));

        // Hashear la contraseña
        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(2, 65536, 1, userDTO.password);

        // Crear entidad Usuario desde el DTO
        Usuario user = new Usuario();
        user.setFullname(userDTO.fullname);
        user.setUsername(userDTO.username);
        user.setPassword(hash);
        user.setRol(rol);

        return userRepository.save(user);
    }

    public Usuario getUsuarioById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(String id, Usuario user) {
        Usuario existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUsuario(String id) {
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
