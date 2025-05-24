package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuario;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(@RequestBody Usuario user) {
        boolean valid = userService.signIn(user);
        if (valid) {
            return ResponseEntity.ok("Welcomo to the Jungle!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
