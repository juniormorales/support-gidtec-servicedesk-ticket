package com.pe.gidtec.servicedesk.ticket.user.repository;

import com.pe.gidtec.servicedesk.ticket.user.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UsersRepository extends ReactiveMongoRepository<UserEntity,String> {

}
