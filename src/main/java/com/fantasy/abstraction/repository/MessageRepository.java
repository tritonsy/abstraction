package com.fantasy.abstraction.repository;

import com.fantasy.abstraction.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
