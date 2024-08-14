package com.volhub.v1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.Applications.ApplicationModal;
import com.volhub.v1.Services.Application.ApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RequestMapping("/application")
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create")
    public ApplicationModal getMethodName(@RequestBody ApplicationModal application) {
        return applicationService.creatApplication(application);
    }

    @GetMapping("/getall")
    public List<ApplicationModal> getMethodName() {
        return applicationService.getApplication();
    }

    @PutMapping("/addvol")
    public String addvol(@RequestParam int applicationId, @RequestParam String volunteerId) {
        return applicationService.updatevolunteers(applicationId, volunteerId);
    }

}
