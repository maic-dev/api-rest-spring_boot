package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Publicacion;

@Repository
public interface IPostRepository extends JpaRepository<Publicacion, String> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar publicaciones por descripción o autor

}
