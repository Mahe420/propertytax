package com.project.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.propertytax.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
