package com.tan.annotation.orm;

import com.tan.annotation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}

// Field userRepository in com.tan.annotation.service.UserService required a bean of type 'com.tan.annotation.orm.UserRepository' that could not be found.