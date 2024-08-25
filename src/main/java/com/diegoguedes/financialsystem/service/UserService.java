package com.diegoguedes.financialsystem.service;

import com.diegoguedes.financialsystem.model.User;
import com.diegoguedes.financialsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
}
