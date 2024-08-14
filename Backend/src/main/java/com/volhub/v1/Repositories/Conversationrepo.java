package com.volhub.v1.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.volhub.v1.Modals.ConverSationModel;

public interface Conversationrepo extends JpaRepository<ConverSationModel, Integer> {

      @Query("SELECT c FROM ConverSationModel c WHERE :member1 MEMBER OF c.members AND :member2 MEMBER OF c.members")
    List<ConverSationModel> findByMembers(@Param("member1") String member1, @Param("member2") String member2);

}
