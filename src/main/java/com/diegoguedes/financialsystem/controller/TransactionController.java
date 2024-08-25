package com.diegoguedes.financialsystem.controller;

import com.diegoguedes.financialsystem.model.Transaction;
import com.diegoguedes.financialsystem.dto.TransactionRequest;
import com.diegoguedes.financialsystem.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoints relacionados às transações financeiras")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Realizar transação", description = "Realiza uma transação financeira entre dois usuários.")
    public void makeTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.processTransaction(transactionRequest.getFromUserId(), 
                                              transactionRequest.getToUserId(), 
                                              transactionRequest.getAmount());
    }

    @GetMapping
    @Operation(summary = "Listar todas as transações", description = "Retorna uma lista de todas as transações realizadas.")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
