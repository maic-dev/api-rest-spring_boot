package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar roles por nombre o id

}
