package com.theduck.note.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.theduck.note.authentication.config.FeignClientInterceptor;
import com.theduck.note.commons.model.ProfileModel;

@FeignClient(name = "profile", configuration = FeignClientInterceptor.class)
public interface ProfileFeignClient {
    
    @PostMapping("/api/profiles")
    ProfileModel createProfile(ProfileModel profileModel);
}
