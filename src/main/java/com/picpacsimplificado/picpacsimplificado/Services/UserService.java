// Criação dos services dos Usuarios

package com.picpacsimplificado.picpacsimplificado.Services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpacsimplificado.picpacsimplificado.domain.user.User;
import com.picpacsimplificado.picpacsimplificado.domain.user.UserType;
import com.picpacsimplificado.picpacsimplificado.dtos.UserDTO;
import com.picpacsimplificado.picpacsimplificado.repositories.UserRepository;

@Service // Anotation para criação da classe Service
public class UserService {

    // A classe UserService tera que ter acesso ao UserRepositiry para pegar os
    // usuario e faer a maupulação dos dados
    @Autowired // Injeção do repositorio na classe repository
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amout) throws Exception { // Metodo para verificação de
                                                                                      // transações
        if (sender.getUserType() == UserType.MERCHANT) { // Verificação que lojista não pode fazer transações
            throw new Exception("Usuario do tipo Lojista não está autorizado a realizar transações");
        }

        if (sender.getBalance().compareTo(amout) < 0) { // Caso o usuario esteja com o saldo insuficiente
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception { // Metodo para encontrar usuario no BD e caso não seja
                                                         // encontrado
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;

    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public void saveUser(User user) { // Metodo para salvaar usuario no BD
        this.repository.save(user);
    }

}