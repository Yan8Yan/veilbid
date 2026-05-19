package com.project.veilbid.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {

    private UUID id;

    private String name;

    private String info;

    private String email;
}