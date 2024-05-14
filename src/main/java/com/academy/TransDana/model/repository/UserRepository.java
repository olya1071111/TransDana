package com.academy.TransDana.model.repository;

import com.academy.TransDana.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String username);


}
