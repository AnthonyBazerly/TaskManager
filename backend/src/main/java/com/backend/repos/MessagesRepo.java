package com.backend.repos;

import com.backend.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepo extends JpaRepository<Messages, Long> {
}
