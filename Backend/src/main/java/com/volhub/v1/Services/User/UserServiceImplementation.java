package com.volhub.v1.Services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.UserModal;
import com.volhub.v1.Repositories.Userrepo;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private Userrepo userrepo;

    @Override
    public void createUser(UserModal user) {
        userrepo.save(user);
    }

    @Override
    public UserModal getUserDetailsById(Long id) {
        return userrepo.findById(id).get();
    }

    @Override
    public UserModal getUserByemail(String email) {
        return userrepo.finduserByemail(email);
    }

    @Transactional
    public String updateprofile(String avatar, Long id) {
        userrepo.updateAvatar(avatar, id);
        return "suc";
    }

    @Transactional
    public String updateone_to_one(Long user, String contact) {
        UserModal usermod = userrepo.findById(user).orElseThrow(() -> new RuntimeException("Application not found"));

        List<String> onetoone = usermod.getOnetoonechat();
        if (!onetoone.contains(contact)) {
            onetoone.add(contact);
            usermod.setOnetoonechat(onetoone);
            userrepo.save(usermod);

        } else {

            return "User is alredy exist on onetoone Chat";
        }
        return "User is added to onetoone Chat";

    }

}
