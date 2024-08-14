package com.volhub.v1.Services.Profile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.ProfileModal;
import com.volhub.v1.Repositories.Profilerepo;

@Service
public class ProfileServiceImplementation implements ProfileService {

    @Autowired
    private Profilerepo profilerepo;
   

 

    @Override
    public ProfileModal getProfileById(Long id) {

        try {
            return profilerepo.findById(id).orElse(null);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching profile by ID: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public ProfileModal createProfile(ProfileModal profile) {
       
           return profilerepo.save(profile);
          
    }
    
    @Override
    public String updateProfileDetails(Long id, ProfileModal profile) {

        Optional<ProfileModal> optionalProfile = profilerepo.findById(id);
        if (optionalProfile.isPresent()) {
            ProfileModal existingProfile = optionalProfile.get();
            existingProfile.setName(profile.getName());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setPhone(profile.getPhone());
            existingProfile.setDob(profile.getDob());
            existingProfile.setStreet(profile.getStreet());
            existingProfile.setCity(profile.getCity());
            existingProfile.setState(profile.getState());
            existingProfile.setZipCode(profile.getZipCode());
            existingProfile.setSkills(profile.getSkills());
            existingProfile.setExperience(profile.getExperience());
            existingProfile.setAreasOfInterest(profile.getAreasOfInterest());
            existingProfile.setLinkedinURL(profile.getLinkedinURL());
            existingProfile.setBankAccountHolderName(profile.getBankAccountHolderName());
            existingProfile.setBankAccNo(profile.getBankAccNo());
            existingProfile.setBankIFSC(profile.getBankIFSC());
            existingProfile.setBankName(profile.getBankName());

            profilerepo.save(existingProfile);
            return "The Profile is Updated";
        }

        return "The Profile is found from db";

    }

   

}
