package com.fantasy.abstraction.repository;

import com.fantasy.abstraction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
