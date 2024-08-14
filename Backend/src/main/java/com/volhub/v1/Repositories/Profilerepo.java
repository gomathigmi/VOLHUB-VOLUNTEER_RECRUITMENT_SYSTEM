package com.volhub.v1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.volhub.v1.Modals.ProfileModal;

public interface Profilerepo extends JpaRepository<ProfileModal,Long> 
{
    
     
}
