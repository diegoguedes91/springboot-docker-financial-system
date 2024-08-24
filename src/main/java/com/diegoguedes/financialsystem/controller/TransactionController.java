package com.diegoguedes.financialsystem.controller;

import com.diegoguedes.financialsystem.model.Transaction;
import com.diegoguedes.financialsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam double amount) {
        return transactionService.createTransaction(senderId, receiverId, amount);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
