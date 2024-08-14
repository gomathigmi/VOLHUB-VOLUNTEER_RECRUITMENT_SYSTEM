package com.volhub.v1.Services.User;

import com.volhub.v1.Modals.UserModal;

public interface UserService {

    public UserModal getUserDetailsById(Long id);

    public void createUser(UserModal user);

    public UserModal getUserByemail(String email);

    public String updateprofile(String avatar, Long id);

    public String updateone_to_one(Long user, String contact);
}
