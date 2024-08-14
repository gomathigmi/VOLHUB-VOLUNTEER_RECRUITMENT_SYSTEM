package com.volhub.v1.Services.Application;

import java.util.List;

import com.volhub.v1.Modals.Applications.ApplicationModal;

public interface ApplicationService {

    public ApplicationModal creatApplication(ApplicationModal Application);

    public List<ApplicationModal> getApplication();

    public String updatevolunteers(int applicationId, String volunteerId);
}
