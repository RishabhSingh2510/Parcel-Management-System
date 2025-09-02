package com.parcelmanagement.backend.repository;

import com.parcelmanagement.backend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    
    Optional<Admin> findByOfficerId(String officerId);
    
    Optional<Admin> findByOfficerIdAndRole(String officerId, String role);
}