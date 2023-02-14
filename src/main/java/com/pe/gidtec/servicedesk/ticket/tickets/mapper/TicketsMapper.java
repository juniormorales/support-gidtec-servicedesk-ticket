package com.pe.gidtec.servicedesk.ticket.tickets.mapper;

import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.MappingHelper;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.TicketPostRequest;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketDetailsResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketResponse;
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
public interface TicketsMapper {

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromTicket")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "ticketId", source = "entity.ticketId")
    @Mapping(target = "issue", source = "entity.issue")
    @Mapping(target = "userCreator", source = "entity.userCreator")
    @Mapping(target = "userCreator.id", source = "entity.userCreator.id")
    @Mapping(target = "userAssigned", source = "entity.userAssigned")
    @Mapping(target = "userAssigned.id", source = "entity.userAssigned.id")
    @Mapping(target = "typeTicket.code", source = "entity.typeCode")
    @Mapping(target = "typeTicket.name", source = "entity",  qualifiedByName = "getTicketTypeDescriptionFromTicket")
    @Mapping(target = "categoryTicket.code", source = "entity.categoryCode")
    @Mapping(target = "categoryTicket.name", source = "entity",  qualifiedByName = "getTicketCategoryDescriptionFromTicket")
    @Mapping(target = "statusTicket.code", source = "entity.statusCode")
    @Mapping(target = "statusTicket.name", source = "entity",  qualifiedByName = "getTicketStatusDescriptionFromTicket")
    TicketResponse entityToTicketResponse(TicketEntity entity);

    @Mapping(target = "audit.statusCode", constant = "AC")
    @Mapping(target = "audit.createdBy", source = "headers.userCode")
    @Mapping(target = "audit.createdDate", expression = "java(getLocalDateTime())")
    @Mapping(target = "audit.deleted", constant = "false")
    @Mapping(target = "userCreator", source = "request.userCreator")
    @Mapping(target = "typeCode", source = "request.type.code")
    @Mapping(target = "categoryCode", source = "request.category.code")
    @Mapping(target = "statusCode", constant = "AB")
    @Mapping(target = "issue", source = "request.issue")
    @Mapping(target = "instance", source = "request.instance")
    @Mapping(target = "duration", constant = "12")
    TicketEntity ticketPostRequestToEntity(TicketPostRequest request, CommonHeaders headers);

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "ticketEntity", qualifiedByName = "getAuditStatusDescriptionFromTicket")
    @Mapping(target = "audit.status.code", source = "ticketEntity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "ticketEntity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "ticketEntity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "ticketEntity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "ticketEntity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "ticketEntity.audit.lastModifiedBy")
    @Mapping(target = "ticketId", source = "ticketEntity.ticketId")
    @Mapping(target = "issue", source = "ticketEntity.issue")
    @Mapping(target = "instance", source = "ticketEntity.instance")
    @Mapping(target = "duration", source = "ticketEntity.duration")
    @Mapping(target = "dueDate", source = "ticketEntity", qualifiedByName = "getDueDate")
    @Mapping(target = "userCreator", source = "ticketEntity.userCreator")
    @Mapping(target = "userCreator.id", source = "ticketEntity.userCreator.id")
    @Mapping(target = "userAssigned", source = "ticketEntity.userAssigned")
    @Mapping(target = "userAssigned.id", source = "ticketEntity.userAssigned.id")
    @Mapping(target = "webSite", source = "userEntity.webSite")
    @Mapping(target = "email", source = "userEntity.email")
    @Mapping(target = "phoneNumber", source = "userEntity.phoneNumber")
    @Mapping(target = "typeTicket.code", source = "ticketEntity.typeCode")
    @Mapping(target = "typeTicket.name", source = "ticketEntity",  qualifiedByName = "getTicketTypeDescriptionFromTicket")
    @Mapping(target = "categoryTicket.code", source = "ticketEntity.categoryCode")
    @Mapping(target = "categoryTicket.name", source = "ticketEntity",  qualifiedByName = "getTicketCategoryDescriptionFromTicket")
    @Mapping(target = "statusTicket.code", source = "ticketEntity.statusCode")
    @Mapping(target = "statusTicket.name", source = "ticketEntity",  qualifiedByName = "getTicketStatusDescriptionFromTicket")
    TicketDetailsResponse entityToTicketDetailResponse(TicketEntity ticketEntity, UserEntity userEntity);

    default LocalDateTime getLocalDateTime() { return LocalDateTime.now(); }

}
