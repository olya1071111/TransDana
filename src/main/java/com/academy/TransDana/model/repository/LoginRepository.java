package com.academy.TransDana.model.repository;

import com.academy.TransDana.model.domain.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByUsername(String username);

    public List<Login> findByRole(String role);

    public List<Login> findByRoleNotLike(String role);

    @Transactional
    @Modifying
    @Query("UPDATE Login l SET l.role = :role, l.enabled = false WHERE l.id = :id")
    void updateRouteStatus(Integer id, String role);
}
