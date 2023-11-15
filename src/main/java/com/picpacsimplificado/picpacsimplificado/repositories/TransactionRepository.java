package com.picpacsimplificado.picpacsimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpacsimplificado.picpacsimplificado.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
