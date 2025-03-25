package com.theduck.note.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theduck.note.profile.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    
}
