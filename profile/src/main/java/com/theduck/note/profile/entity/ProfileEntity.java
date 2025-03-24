package com.theduck.note.profile.entity;

import java.util.Date;

import com.theduck.note.commons.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long profileId;

    private Long accountId;

    private String fullName;

    private String gender;

    private Date dateOfBirth;
}
