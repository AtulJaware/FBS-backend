package com.fbs.User.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbs.User.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUsername(String username);

}
