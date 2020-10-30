package com.project.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.propertytax.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone,Integer> {

	Zone findByName(String name);

}
