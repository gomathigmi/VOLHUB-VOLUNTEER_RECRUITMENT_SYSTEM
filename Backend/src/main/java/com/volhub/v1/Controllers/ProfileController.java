package com.volhub.v1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.ProfileModal;
import com.volhub.v1.Services.Profile.ProfileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileservice;

    @GetMapping("/get")
    public ProfileModal getMethodName(@RequestParam Long id) {
        return profileservice.getProfileById(id);
    }

    @PostMapping("/create")
    public ProfileModal create(@RequestBody ProfileModal profile) {
        return profileservice.createProfile(profile);

    }

    @PutMapping("/update")
    public String updateProfile(@RequestParam Long id, @RequestBody ProfileModal profile) {
        String res = profileservice.updateProfileDetails(id, profile);

        return res;
    }
   


}
