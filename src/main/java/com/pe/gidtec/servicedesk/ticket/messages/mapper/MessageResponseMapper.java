package com.pe.gidtec.servicedesk.ticket.messages.mapper;

import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.MappingHelper;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePostRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.MessageGetResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.TotalMessageResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {MappingHelper.class},
        builder = @Builder(disableBuilder = true)
)
public interface MessageResponseMapper {

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromMessages")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "messageId", source = "entity.messageId")
    @Mapping(target = "userSenderId", source = "entity.userSenderId")
    @Mapping(target = "messageParentId", source = "entity.messageParentId")
    @Mapping(target = "messageResponse", source = "entity.messageResponse")
    MessageGetResponse entityToMessageGetResponse(MessageResponseEntity entity);

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromMessages")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "messageId", source = "entity.messageId")
    @Mapping(target = "userSenderId", source = "entity.userSenderId")
    @Mapping(target = "messageResponse", source = "entity.messageResponse")
    TotalMessageResponse entityToTotalMessageResponseWhitOutChild(MessageResponseEntity entity);

    @Mapping(target = "audit.statusCode", constant = "AC")
    @Mapping(target = "audit.createdBy", source = "headers.userCode")
    @Mapping(target = "audit.createdDate", expression = "java(getLocalDateTime())")
    @Mapping(target = "audit.deleted", constant = "false")
    @Mapping(target = "userSenderId", source = "request.userSenderId")
    @Mapping(target = "ticketId",  source = "request.ticketId")
    @Mapping(target = "messageParentId", source = "request.messageParentId")
    @Mapping(target = "messageResponse", source = "request.messageResponse")
    MessageResponseEntity messagePostRequestToEntity(MessagePostRequest request, CommonHeaders headers);

    default LocalDateTime getLocalDateTime() { return LocalDateTime.now(); }

}
