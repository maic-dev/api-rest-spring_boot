package com.example.demo.dto;

//Usa DTOs para controlar datos enviados y recibidos en API.
public class PostDTO {

    private String description;

    private Long usuarioId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
