package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Publicacion;

public interface IPostRepository extends JpaRepository<Publicacion, Long> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar publicaciones por descripción o autor

}
