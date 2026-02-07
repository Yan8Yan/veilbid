package com.project.veilbid.mappers;

import com.project.veilbid.domain.dto.CreateLotRequestDTO;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.requests.CreateLotRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LotMapper {
    CreateLotRequest fromDTO(CreateLotRequestDTO dto);
    CreateLotResponseDTO toDTO(Lot lot);
}
