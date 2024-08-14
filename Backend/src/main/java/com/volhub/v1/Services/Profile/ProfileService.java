package com.volhub.v1.Services.Profile;

import com.volhub.v1.Modals.ProfileModal;

public interface ProfileService {

    public ProfileModal createProfile(ProfileModal profile);

    public String updateProfileDetails(Long id, ProfileModal profile);

    public ProfileModal getProfileById(Long id);

  



}
