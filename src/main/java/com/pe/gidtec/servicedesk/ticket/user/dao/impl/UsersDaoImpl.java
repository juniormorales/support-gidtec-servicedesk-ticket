package com.pe.gidtec.servicedesk.ticket.user.dao.impl;

import com.pe.gidtec.servicedesk.ticket.user.dao.UsersDao;
import com.pe.gidtec.servicedesk.ticket.user.model.entity.UserEntity;
import com.pe.gidtec.servicedesk.ticket.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersDaoImpl implements UsersDao {

    private final UsersRepository usersRepository;

    @Override
    public Mono<UserEntity> getUserById(String userId) {
        return usersRepository.findById(userId);
    }

}
