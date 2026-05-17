package com.project.veilbid.mappers;

import com.project.veilbid.domain.dto.CreateBidRequestDTO;
import com.project.veilbid.domain.dto.CreateBidResponseDTO;
import com.project.veilbid.domain.entities.Bid;
import com.project.veilbid.domain.requests.CreateBidRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BidMapper {

    @Mapping(source = "id", target = "id")
    CreateBidResponseDTO toDTO(Bid bid);

    CreateBidRequest fromDTO(CreateBidRequestDTO dto);
}