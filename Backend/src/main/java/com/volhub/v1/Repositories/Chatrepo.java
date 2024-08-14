package com.volhub.v1.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.volhub.v1.Modals.MessageModal;

public interface Chatrepo extends JpaRepository<MessageModal, Integer> {

    @Query("SELECT m FROM MessageModal m WHERE m.conversationid = :conversationid")
    List<MessageModal> findByConversationId(@Param("conversationid") String conversationId);

}
