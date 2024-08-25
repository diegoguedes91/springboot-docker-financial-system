package com.diegoguedes.financialsystem.controller;

import com.diegoguedes.financialsystem.model.User;
import com.diegoguedes.financialsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints relacionados ao gerenciamento de usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário com um saldo inicial.")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
