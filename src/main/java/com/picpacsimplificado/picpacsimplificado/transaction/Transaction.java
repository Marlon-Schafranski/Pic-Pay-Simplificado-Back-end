// Criação das transações de valores

package com.picpacsimplificado.picpacsimplificado.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.picpacsimplificado.picpacsimplificado.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // dizer que a classe sera uma entitade no BD
@Table(name = "transactions") // criação da tabela no DB
@Getter // criação dos getter
@Setter // Criação dos Setters
@AllArgsConstructor // criação de todos os crantrutores da clase
@NoArgsConstructor
@EqualsAndHashCode(of = "id") // criação da primaryKey que sera a id
public class Transaction {

    @Id // criação do id da classe
    @GeneratedValue(strategy = GenerationType.IDENTITY) // criação das id altomatica
    private Long id;

    private BigDecimal amout; // valor da transação

    // referencia de onde foi a transferencia da transação

    @ManyToOne // Essa annotation significa que 1 usuario pode ter varias transações.
               // Mais uma transação pode ter so um usuario
    @JoinColumn(name = "sender_id") //
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver_id;

    private LocalDateTime timetamp; // quanto foi realizado nossa transação

}