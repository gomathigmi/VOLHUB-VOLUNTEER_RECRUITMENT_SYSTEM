package com.volhub.v1.Services.Application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.Applications.ApplicationModal;
import com.volhub.v1.Repositories.Applicationrepo;

import jakarta.transaction.Transactional;

@Service
public class ApplicationServiceImplementation implements ApplicationService {

    @Autowired
    private Applicationrepo applicationrepo;

    public ApplicationModal creatApplication(ApplicationModal Application) {
        return applicationrepo.save(Application);
    }

    public List<ApplicationModal> getApplication() {
        return applicationrepo.findAll();
    }

    @Transactional
    public String updatevolunteers(int applicationId, String volunteerId) {
        ApplicationModal application = applicationrepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        List<String> volunteers = application.getVolunteers();
        if (!volunteers.contains(volunteerId)) {
            volunteers.add(volunteerId);
            application.setVolunteers(volunteers);
            applicationrepo.save(application);
        }
        else
        {

            return "The Volunter is alredy includes";
        }
        return "The Volunter is added to application";
    }

}
