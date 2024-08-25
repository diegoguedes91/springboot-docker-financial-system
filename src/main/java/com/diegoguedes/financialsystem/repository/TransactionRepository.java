package com.diegoguedes.financialsystem.repository;

import com.diegoguedes.financialsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}