package com.gonie.fullStackbackend.repository;

import com.gonie.fullStackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
