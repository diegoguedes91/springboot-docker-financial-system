package com.diegoguedes.financialsystem.repository;

import com.diegoguedes.financialsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
