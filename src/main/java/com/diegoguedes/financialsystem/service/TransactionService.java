package com.diegoguedes.financialsystem.service;

import com.diegoguedes.financialsystem.model.Transaction;
import com.diegoguedes.financialsystem.model.User;
import com.diegoguedes.financialsystem.repository.TransactionRepository;
import com.diegoguedes.financialsystem.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Transaction createTransaction(Long senderId, Long receiverId, double amount) {
        User sender = userRepository.findById(senderId).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();

        if (sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            userRepository.save(sender);
            userRepository.save(receiver);

            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());

            transactionRepository.save(transaction);

            // Envia mensagem via RabbitMQ
            rabbitTemplate.convertAndSend("transactions.exchange", "transactions.routingkey", transaction);

            return transaction;
        } else {
            throw new RuntimeException("Saldo insuficiente!");
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
