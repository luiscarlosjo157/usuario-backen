package com.usuarios_resena.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarios_resena.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
