package com.pe.gidtec.servicedesk.ticket.historical.mapper;

import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.MappingHelper;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.response.HistoricalGetResponse;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.resquest.HistoricalPostRequest;
import com.pe.gidtec.servicedesk.ticket.historical.model.entity.HistoricalEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import com.pe.gidtec.servicedesk.ticket.user.model.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {MappingHelper.class},
        builder = @Builder(disableBuilder = true)
)
public interface HistoricalMapper {

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "historicalEntity", qualifiedByName = "getAuditStatusDescriptionFromHistorical")
    @Mapping(target = "audit.status.code", source = "historicalEntity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "historicalEntity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "historicalEntity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "historicalEntity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "historicalEntity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "historicalEntity.audit.lastModifiedBy")
    @Mapping(target = "ticketId", source = "ticketEntity.ticketId")
    @Mapping(target = "issue", source = "ticketEntity.issue")
    @Mapping(target = "historicalId", source = "historicalEntity.historicalId")
    @Mapping(target = "reason", source = "historicalEntity.reason")
    @Mapping(target = "userAssigned.id", source = "historicalEntity.userAssignedId")
    @Mapping(target = "userAssigned.names", source = "userEntityAssigned.names")
    @Mapping(target = "userAssigned.lastNames", source = "userEntityAssigned.lastNames")
    @Mapping(target = "userAssigned.companyName", source = "userEntityAssigned.companyName")
    @Mapping(target = "userReassigned.id", source = "historicalEntity.userReassignedId")
    @Mapping(target = "userReassigned.names", source = "userEntityReassigned.names")
    @Mapping(target = "userReassigned.lastNames", source = "userEntityReassigned.lastNames")
    @Mapping(target = "userReassigned.companyName", source = "userEntityReassigned.companyName")
    HistoricalGetResponse entityToHistoricalGetResponse(HistoricalEntity historicalEntity, TicketEntity ticketEntity, UserEntity userEntityAssigned, UserEntity userEntityReassigned);

    @Mapping(target = "audit.statusCode", constant = "AC")
    @Mapping(target = "audit.createdBy", source = "headers.userCode")
    @Mapping(target = "audit.createdDate", expression = "java(getLocalDateTime())")
    @Mapping(target = "audit.deleted", constant = "false")
    @Mapping(target = "userAssignedId", source = "request.userAssignedId")
    @Mapping(target = "ticketId", source = "request.ticketId")
    @Mapping(target = "reason", source = "request.reason")
    @Mapping(target = "userReassignedId", source = "request.userReassignedId")
    HistoricalEntity historicalPostRequestToEntity(HistoricalPostRequest request, CommonHeaders headers);

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromHistorical")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "historicalId", source = "entity.historicalId")
    @Mapping(target = "reason", source = "entity.reason")
    @Mapping(target = "userAssigned.id", source = "entity.userAssignedId")
    @Mapping(target = "userReassigned.id", source = "entity.userReassignedId")
    HistoricalGetResponse entityToHistoricalResponse(HistoricalEntity entity);

    default LocalDateTime getLocalDateTime() { return LocalDateTime.now(); }


}
