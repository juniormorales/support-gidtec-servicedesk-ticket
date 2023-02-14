package com.pe.gidtec.servicedesk.ticket.user.dao;

import com.pe.gidtec.servicedesk.ticket.user.model.entity.UserEntity;
import reactor.core.publisher.Mono;

public interface UsersDao {

    Mono<UserEntity> getUserById(String userId);

}
