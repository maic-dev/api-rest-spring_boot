package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDTO;
import com.example.demo.models.Publicacion;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/post")
    public List<Publicacion> getAllPosts() {
        return postService.getPublicaciones();
    }

    @PostMapping(value = "/post")
    public Publicacion createPost(@RequestBody PostDTO postDTO) {
        return postService.savePublicacion(postDTO);
    }
}
