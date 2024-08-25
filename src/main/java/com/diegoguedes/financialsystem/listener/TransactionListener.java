package com.diegoguedes.financialsystem.listener;

import com.diegoguedes.financialsystem.model.Transaction;
import com.diegoguedes.financialsystem.service.UserService;
import com.diegoguedes.financialsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionListener {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    public void receiveMessage(Map<String, Object> transactionDetails) {
        Long fromUserId = (Long) transactionDetails.get("fromUserId");
        Long toUserId = (Long) transactionDetails.get("toUserId");
        double amount = (double) transactionDetails.get("amount");

        // Atualiza os saldos dos usuários
        userService.updateBalance(fromUserId, -amount);
        userService.updateBalance(toUserId, amount);

        // Salva a transação após processá-la
        Transaction transaction = new Transaction(fromUserId, toUserId, amount);
        transactionRepository.save(transaction);
    }
}
