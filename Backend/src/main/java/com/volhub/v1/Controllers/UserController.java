package com.volhub.v1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.ProfileModal;
import com.volhub.v1.Modals.UserModal;
import com.volhub.v1.Services.Profile.ProfileService;
import com.volhub.v1.Services.User.UserService;
import com.volhub.v1.Socket.SocketIOService;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private ProfileService profileservice;

    private SocketIOService socketIOService;

    @Autowired
    public void test(SocketIOService socketIOService) {
        this.socketIOService = socketIOService;
    }

    @GetMapping("/getbyid")
    public UserModal getUserDetailsById(@RequestParam Long id) {
        return userservice.getUserDetailsById(id);
    }

    @GetMapping("/get")
    public UserModal getUserByEmail(@RequestParam String email) {
        return userservice.getUserByemail(email);
    }

    @PostMapping("/create")
    public String createNewUser(@RequestBody UserModal user) {

        ProfileModal profile = new ProfileModal();

        profile.setName(user.getName());
        profile.setPhone(user.getPhno());
        profile.setEmail(user.getEmail());

        ProfileModal createdProfile = profileservice.createProfile(profile);
        user.setProfile(createdProfile);
        userservice.createUser(user);
        return "User Created Successfully !";
    }

    @PutMapping("/update/avatar")
    public String updateavatar(@RequestParam Long id, @RequestParam String avatar) {
        String res = userservice.updateprofile(avatar, id);
        return res;
    }

    @PostMapping("/create/onechat")
    public String postMethodName(@RequestParam Long user, @RequestParam String contact) {

        return userservice.updateone_to_one(user, contact);
    }

    @GetMapping("/add")
    public String postMethodName() {
        System.out.println("hell..");

        socketIOService.getServer().getBroadcastOperations().sendEvent("notification", "A new student has been saved!");

        return "The student is saved.";
    }

}
