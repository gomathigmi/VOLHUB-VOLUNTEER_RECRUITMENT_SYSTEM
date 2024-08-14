package com.volhub.v1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volhub.v1.Modals.Post.PostModel;

public interface Postrepo extends JpaRepository<PostModel,Integer> {
    
}
