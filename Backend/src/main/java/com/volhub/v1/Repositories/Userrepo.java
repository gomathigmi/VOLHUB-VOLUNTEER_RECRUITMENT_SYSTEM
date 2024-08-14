package com.volhub.v1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.volhub.v1.Modals.UserModal;

import jakarta.transaction.Transactional;

public interface Userrepo extends JpaRepository<UserModal, Long> {

    @Query("SELECT m FROM UserModal m WHERE m.email = :email")
    public UserModal finduserByemail(@Param("email") String email);

    @Modifying
@Transactional
@Query("UPDATE UserModal p SET p.avatar = :avatar WHERE p.id = :id")
int updateAvatar(@Param("avatar") String avatar, @Param("id") Long id);
}
