package com.theduck.note.profile.service;

import org.springframework.stereotype.Service;

import com.theduck.note.commons.model.ProfileModel;
import com.theduck.note.profile.entity.ProfileEntity;
import com.theduck.note.profile.mapper.ProfileMapper;
import com.theduck.note.profile.repository.ProfileRepository;

@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public ProfileModel createProfile(ProfileModel profile) {
        ProfileEntity profileEntity = this.profileMapper.toEntity(profile);
        ProfileEntity result = this.profileRepository.save(profileEntity);
        return this.profileMapper.toModel(result);
    }
}
