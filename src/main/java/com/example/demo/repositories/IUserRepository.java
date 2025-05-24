package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Usuario;

//Estos IRepositories son interfaces que extienden de JpaRepository
//Se encargan de definir y ejecutar las consultas directas a las db

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Long> {

    @Query("FROM Usuario u WHERE u.username = :username AND u.password = :password")
    Usuario signIn(@Param("username") String username, @Param("password") String password);

    Usuario findByUsername(String username);

}
