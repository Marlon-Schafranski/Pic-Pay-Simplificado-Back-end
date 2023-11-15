package com.picpacsimplificado.picpacsimplificado.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpacsimplificado.picpacsimplificado.domain.user.User;
import com.picpacsimplificado.picpacsimplificado.dtos.TransactionDTO;
import com.picpacsimplificado.picpacsimplificado.repositories.TransactionRepository;
import com.picpacsimplificado.picpacsimplificado.transaction.Transaction;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate; // a classe RestTemplate é uma classe que o Spring oferece para fazer
                                       // comunicação HTTP entre servicos

    @Autowired
    private NotificationService notificationService;

    // Essa TransactionDTO é os dados que vai receber no payload quanto o usuario
    // fazer a requisição no POST
    public Transaction createTransaction(TransactionDTO transaction) throws Exception { // MEtdo para criaações das
                                                                                        // transações
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value()); // criação da validação para transação

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        // PEgar as informações dos usuarios
        Transaction newTransaction = new Transaction();
        newTransaction.setAmout(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver_id(receiver);
        newTransaction.setTimetamp(LocalDateTime.now());

        // Atualizar o saldos dos usuarios e salvar a transação no BD e usuarios
        sender.setBalance(sender.getBalance().subtract(transaction.value()));// subtrair o saldo do usuario

        receiver.setBalance((receiver.getBalance().add(transaction.value()))); // Adicionar o valor da transação para o
                                                                               // lojista

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!!! ");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!!! ");

        return newTransaction;

    }

    // Criação da autoriazação da transição em HTTP
    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationReponse = restTemplate
                .getForEntity("https://run.mocky.io/v3/6f935d4c-12e5-420d-b614-eb0fcfd01a94", Map.class);

        if (authorizationReponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationReponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);

        } else
            return false;
    }

}