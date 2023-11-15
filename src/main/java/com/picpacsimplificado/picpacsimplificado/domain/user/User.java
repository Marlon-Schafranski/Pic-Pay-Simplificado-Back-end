//Criação dos usuario 

package com.picpacsimplificado.picpacsimplificado.domain.user;

import java.math.BigDecimal;

import com.picpacsimplificado.picpacsimplificado.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "users") // Usado para dizer que sera a classe sera a entidade no BD
@Table(name = "users") // Usado para dizer qual sera o nome desta tabala esta representado
@Data // Usado para criação de Getters e Setters
@AllArgsConstructor // criação de todos os crantrutores da clase
@NoArgsConstructor //é responsável por gerar um construtor sem parâmetros
@EqualsAndHashCode(of = "id") // criação da primaryKey da entidade
public class User {
    @Id // Identificação de ID para o usuario
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração das ID
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true) // CPF ou CNPJ como sento unicos
    private String document;

    @Column(unique = true) // Email como sento unico
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING) // utilizado para dizer que o usuario vai ser Lojista ou pessoa comum
    private UserType userType;

    public User(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();

        // this.balance = BigDecimal.ZERO;

    }

}