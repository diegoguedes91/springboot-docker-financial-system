package com.diegoguedes.financialsystem.service;

import com.diegoguedes.financialsystem.model.User;
import com.diegoguedes.financialsystem.model.Transaction;
import com.diegoguedes.financialsystem.repository.TransactionRepository;
import com.diegoguedes.financialsystem.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void processTransaction(Long fromUserId, Long toUserId, double amount) {
        // Buscar os usuários
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("Usuário remetente não encontrado"));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Usuário destinatário não encontrado"));

        // Verificar se o remetente tem saldo suficiente
        if (fromUser.getBalance() < amount) {
            throw new RuntimeException("Saldo insuficiente para realizar a transação");
        }

        // Deduzir o saldo do remetente e aumentar o saldo do destinatário
        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        // Salvar os usuários com os novos saldos
        userRepository.save(fromUser);
        userRepository.save(toUser);

        // Criar e salvar a transação
        Transaction transaction = new Transaction(fromUserId, toUserId, amount);
        transactionRepository.save(transaction);

        // Opcional: Enviar a transação para o RabbitMQ
        Map<String, Object> transactionDetails = new HashMap<>();
        transactionDetails.put("fromUserId", fromUserId);
        transactionDetails.put("toUserId", toUserId);
        transactionDetails.put("amount", amount);
        rabbitTemplate.convertAndSend("transaction-exchange", "transaction-routing-key", transactionDetails);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}