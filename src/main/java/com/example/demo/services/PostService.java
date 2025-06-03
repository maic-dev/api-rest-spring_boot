package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Publicacion;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.IPostRepository;
import com.example.demo.repositories.IUserRepository;

@Service
public class PostService {

    @Autowired
    IPostRepository postRepository;
    // Aquí puedes definir métodos para manejar las publicaciones
    // Por ejemplo, para crear, actualizar, eliminar o buscar publicaciones

    @Autowired
    IUserRepository userRepository;

    public ArrayList<Publicacion> getPublicaciones() {
        return (ArrayList<Publicacion>) postRepository.findAll();
    }

    public Publicacion savePublicacion(PostDTO postDTO) {
        Usuario usuario = userRepository.findById(postDTO.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + postDTO.usuarioId));

        Publicacion publicacion = new Publicacion();
        publicacion.setDescription(postDTO.description);
        publicacion.setUsuario(usuario);
        return postRepository.save(publicacion);
    }
}
