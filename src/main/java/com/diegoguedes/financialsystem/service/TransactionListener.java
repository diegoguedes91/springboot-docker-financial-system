package com.diegoguedes.financialsystem.service;

import com.diegoguedes.financialsystem.config.RabbitMQConfig;
import com.diegoguedes.financialsystem.model.Transaction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Transaction transaction) {
        System.out.println("Recebida transação: " + transaction);
        // Aqui você pode processar a transação recebida
    }
}