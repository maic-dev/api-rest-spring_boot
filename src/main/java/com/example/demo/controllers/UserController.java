package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.Usuario;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public List<Usuario> getAllUsers() {
        return userService.getUsuarios();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        try {
            Usuario savedUser = userService.saveUsuario(userDTO);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping(path = "/user/{id}")
    public Usuario getUserById(@PathVariable String id) {
        return userService.getUsuarioById(id);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUsuario(id);
    }

    @PatchMapping(path = "/user/{id}")
    public Usuario updateUser(@PathVariable String id, @RequestBody Usuario user) {
        return userService.updateUsuario(id, user);
    }

}
