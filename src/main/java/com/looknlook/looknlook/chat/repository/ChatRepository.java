package com.looknlook.looknlook.chat.repository;

import com.looknlook.looknlook.chat.domain.entity.Chat;
import com.looknlook.looknlook.chat.domain.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
}
