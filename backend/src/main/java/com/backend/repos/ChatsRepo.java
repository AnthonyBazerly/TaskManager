package com.backend.repos;

import com.backend.models.Chats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepo extends JpaRepository<Chats, Long> {
}
