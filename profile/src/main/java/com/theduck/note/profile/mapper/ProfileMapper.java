package com.theduck.note.profile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.theduck.note.commons.model.ProfileModel;
import com.theduck.note.profile.entity.ProfileEntity;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileEntity toEntity(ProfileModel profileModel);

    ProfileModel toModel(ProfileEntity profileEntity);
}
