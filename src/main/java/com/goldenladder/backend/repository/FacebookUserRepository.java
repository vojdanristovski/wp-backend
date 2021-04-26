package com.goldenladder.backend.repository;

import com.goldenladder.backend.model.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacebookUserRepository extends JpaRepository<FacebookUser, String> {
    Optional<FacebookUser> findById(String username);
}
