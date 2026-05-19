package com.project.veilbid.mappers;

import com.project.veilbid.domain.dto.CreateLotRequestDTO;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.domain.dto.UserProfileDTO;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.domain.requests.CreateLotRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface LotMapper {

    CreateLotRequest fromDTO(CreateLotRequestDTO dto);

    CreateLotResponseDTO toDTO(Lot lot);

    UserProfileDTO userToUserProfileDTO(User user);
}