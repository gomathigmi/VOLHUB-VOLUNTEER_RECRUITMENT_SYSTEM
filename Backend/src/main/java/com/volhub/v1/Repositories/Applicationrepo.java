package com.volhub.v1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volhub.v1.Modals.Applications.ApplicationModal;

public interface Applicationrepo extends JpaRepository<ApplicationModal,Integer> {
    
}
