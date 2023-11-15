package com.picpacsimplificado.picpacsimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpacsimplificado.picpacsimplificado.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserBydocument(String document); // Definição de um metodo que vai buscar pelo documento CPF/CNPJ

    Optional<User> findUserById(Long id); // Definição de um metodo que vai buscar pelo ID

    // Isso pode retornar o Usuario (caso esteja cadastrado) ou não pode retornar o
    // usuario(caso ele não esteja cadastrado)

}
