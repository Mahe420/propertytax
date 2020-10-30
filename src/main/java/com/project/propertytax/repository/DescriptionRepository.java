package com.project.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.propertytax.entity.Description;
import com.project.propertytax.entity.Status;

public interface DescriptionRepository  extends JpaRepository<Description,Integer>{

	Description findByDescriptionAndStatus(String description,Status status);

}
